package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.UsersDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Users;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.annotation.*;
import jakarta.inject.*;

import java.io.Serializable;
import java.io.IOException;
@ViewScoped
@Named("ProfileBean")
public class ProfileBean implements Serializable {

    @Inject
    private LoginBean loginBean;

    @Inject
    private UsersDAO usersDAO;

    private Users user;
    private  Users editableUser;
    private boolean editMode;

    @PostConstruct
    public void init() {
        user = loginBean.getLoggedInUser();

        if ( user == null) {
            try{
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .redirect("../pages/dynamic/login.xhtml?faces-redirect=true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        user = usersDAO.getUserByID(user.getId());
        editMode = false;
    }

    public void startEdit()
    {
        editableUser =new Users();
        editableUser.setId(user.getId());
        editableUser.setFirstName(user.getFirstName());
        editableUser.setLastName(user.getLastName());
        editableUser.setEmail(user.getEmail());
        editMode = true;
    }
    public void applyChanges(){
        try{
            if(editableUser != null){
                user.setFirstName(editableUser.getFirstName());
                user.setLastName(editableUser.getLastName());
                user.setEmail(editableUser.getEmail());
                usersDAO.updateUser(editableUser);

                usersDAO.updateUser(user);

                loginBean.setLoggedInUser(user);

                editMode = false;

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "User profile Updated"));

            }
        }catch (Exception e){
            e.printStackTrace();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            "User profile  failed to Update"));
        }

    }
    public  void cancelEdit(){
        editMode = false;
        editableUser = null;
    }
    public Users getUser() {
        return user;
    }

    public Users getEditableUser() {
            return this.editableUser;
    }
    public boolean isEditMode() {
        return editMode;
    }

    public void setEditableUser(final Users editableUser) {
            this.editableUser = editableUser;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
