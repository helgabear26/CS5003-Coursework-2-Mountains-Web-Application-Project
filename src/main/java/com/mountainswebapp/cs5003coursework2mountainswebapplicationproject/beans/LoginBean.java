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

    private String email;
    private String password;
    private Users loggedInUser;

    public String login() {

        boolean hasError = false;

        // Check empty email
        if (email == null || email.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("loginForm:email",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Email is required"));
            hasError = true;
        }

        // Check empty password
        if (password == null || password.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("loginForm:password",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Password is required"));
            hasError = true;
        }

        // Stop if there are field errors
        if (hasError) {
            return null;
        }

        // TEMP login check (replace with database later)
        Users user = null;

        if (email.equals("test@example.com") && password.equals("password123")) {
            user = new Users(); // replace with real DB user later
        }

        // Invalid login
        if (user == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Incorrect email or password"));
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}