package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;

// IMPORTANT 👇
// use -> private Users loggedInUser

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Users;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private Users loggedInUser;

    public String login() {

        boolean hasError = false;

        // Check empty username
        if (username == null || username.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("loginForm:username",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Username is required."));
            hasError = true;
        }

        // Check empty password
        if (password == null || password.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("loginForm:password",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Password is required."));
            hasError = true;
        }

        // Stop if there are field errors
        if (hasError) {
            return null;
        }

        // TEMP login check (replace with database later)
        Users user = null;

        if (username.equals("testuser") && password.equals("password123")) {
            user = new Users(); // replace with real DB user later
        }

        // Invalid login
        if (user == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Incorrect username and/or password."));
            return null;
        }

        // Successful login
        loggedInUser = user;

        return "/pages/dynamic/homePage.xhtml?faces-redirect=true";
    }

    public String logout() {
        loggedInUser = null;
        return "/pages/dynamic/login.xhtml?faces-redirect=true";
    }

    public Users getLoggedInUser() {
        return loggedInUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}