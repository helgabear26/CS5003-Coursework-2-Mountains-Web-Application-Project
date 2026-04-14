package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.UsersDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Users;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.inject.*;
import jakarta.faces.application.*;
import jakarta.faces.context.*;

import java.io.Serializable;

@Named("SignUpBean")
@RequestScoped
public class SignUpBean implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;

    @Inject
    private UsersDAO usersDAO;

    public String register()
    {
        if(!password.equals(confirmPassword))
        {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Password does not match",null));
            return null;
        }

        if(usersDAO.getUserByUsername(username) != null)
        {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Username already in use" +"TRY another one",null));
            return null;
        }

        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        usersDAO.createUser(user);

        return "../pages/dynamic/homePage.xhtml?faces-redirect=true";
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public String getEmail()
        {
        return email;
        }
    public  void setEmail(String email)
    {
        this.email = email;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}
