package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;

import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Users;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.UsersDAO;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private Users loggedInUser;

    @Inject
    private UsersDAO usersDAO;

    public String login() {

        boolean hasError = false;

        if (username == null || username.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("loginForm:username",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Username is required."));
            hasError = true;
        }

        if (password == null || password.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("loginForm:password",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Password is required."));
            hasError = true;
        }

        if (hasError) {
            return null;
        }

        Users user = usersDAO.authenticateUser(username, password);

        if (user == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Incorrect username and/or password."));
            return null;
        }

        loggedInUser = user;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser", user);
        username = null;
        password = null;
        return "/pages/dynamic/homePage.xhtml?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        loggedInUser = null;
        username = null;
        password = null;
        return "/pages/dynamic/login.xhtml?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public Users getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(Users loggedInUser) {
        this.loggedInUser = loggedInUser;
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