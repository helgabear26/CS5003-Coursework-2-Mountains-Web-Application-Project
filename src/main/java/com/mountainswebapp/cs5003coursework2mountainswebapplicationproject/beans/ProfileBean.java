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

    // Bean used to access the logged-in user
    @Inject
    private LoginBean loginBean;

    // DAO used to update and retrieve user data
    @Inject
    private UsersDAO usersDAO;

    // Current user data
    private Users user;

    // Temporary editable copy of user data
    private Users editableUser;

    // Controls whether edit mode is active
    private boolean editMode;

    // Initialises user data when page loads
    @PostConstruct
    public void init() {

        // Get logged-in user
        user = loginBean.getLoggedInUser();

        // Redirect to login page if no user is logged in
        if (user == null) {
            try {
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .redirect("../pages/dynamic/login.xhtml?faces-redirect=true");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Refresh user data from database
        user = usersDAO.getUserByID(user.getId());

        // Set edit mode to false initially
        editMode = false;
    }

    // Starts edit mode and copies user data into editable object
    public void startEdit() {

        editableUser = new Users();

        editableUser.setId(user.getId());
        editableUser.setFirstName(user.getFirstName());
        editableUser.setLastName(user.getLastName());
        editableUser.setEmail(user.getEmail());
        editableUser.setUsername(user.getUsername());

        editMode = true;
    }

    // Applies changes made by the user
    public void applyChanges() {
        try {
            if (editableUser != null) {

                // Update user object with edited values
                user.setFirstName(editableUser.getFirstName());
                user.setLastName(editableUser.getLastName());
                user.setEmail(editableUser.getEmail());
                user.setUsername(editableUser.getUsername());

                // Save updated user to database
                usersDAO.updateUser(user);

                // Update logged-in user session
                loginBean.setLoggedInUser(user);

                // Exit edit mode
                editMode = false;

                // Show success message
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "User profile Updated"));
            }
        } catch (Exception e) {
            e.printStackTrace();

            // Show error message if update fails
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            "User profile failed to Update"));
        }
    }

    // Cancels edit mode and clears temporary data
    public void cancelEdit() {
        editMode = false;
        editableUser = null;
    }

    // Returns current user
    public Users getUser() {
        return user;
    }

    // Returns editable user
    public Users getEditableUser() {
        return this.editableUser;
    }

    // Returns edit mode status
    public boolean isEditMode() {
        return editMode;
    }

    // Sets editable user
    public void setEditableUser(final Users editableUser) {
        this.editableUser = editableUser;
    }

    // Sets user
    public void setUser(Users user) {
        this.user = user;
    }
}