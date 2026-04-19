package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;

import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import org.mindrot.jbcrypt.BCrypt;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Users;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.UsersDAO;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    // Stores username entered by user
    private String username;

    // Stores password entered by user
    private String password;

    // Stores currently logged-in user
    private Users loggedInUser;

    // DAO used to retrieve user data from database
    @Inject
    private UsersDAO usersDAO;

    // Handles user login process
    public String login() {

        boolean hasError = false;

        // Validate username field
        if (username == null || username.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("loginForm:username",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Username is required."));
            hasError = true;
        }

        // Validate password field
        if (password == null || password.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("loginForm:password",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Password is required."));
            hasError = true;
        }

        // Stop login process if validation fails
        if (hasError) {
            return null;
        }

        // Retrieve user from database by username
        Users user = usersDAO.getUserByUsername(username);

        // Check if user exists and password matches (using BCrypt hashing)
        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Incorrect username and/or password."));
            return null;
        }

        // Store logged-in user in session
        loggedInUser = user;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser", user);

        // Clear input fields after successful login
        username = null;
        password = null;

        // Redirect to home page
        return "/pages/dynamic/homePage.xhtml?faces-redirect=true";
    }

    // Handles user logout
    public String logout() {

        // Invalidate session and clear user data
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        loggedInUser = null;
        username = null;
        password = null;

        // Redirect to login page
        return "/pages/dynamic/login.xhtml?faces-redirect=true";
    }

    // Checks if a user is logged in
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    // Returns logged-in user
    public Users getLoggedInUser() {
        return loggedInUser;
    }

    // Sets logged-in user
    public void setLoggedInUser(Users loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }
}