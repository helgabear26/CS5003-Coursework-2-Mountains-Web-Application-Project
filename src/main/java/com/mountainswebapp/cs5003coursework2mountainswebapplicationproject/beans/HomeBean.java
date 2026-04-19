package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.*;
import java.io.Serializable;

@Named("homeBean")
@ViewScoped
public class HomeBean implements Serializable {

    // Injects LoginBean to access logged-in user details
    @Inject
    private LoginBean loginBean;

    // Checks if a user is currently logged in
    public boolean isLoggedIn() {
        return loginBean.getLoggedInUser() != null;
    }

    // Returns the username of the logged-in user
    public String getUsername() {
        if (loginBean.getLoggedInUser() != null) {
            return loginBean.getLoggedInUser().getUsername();
        }
        return "";
    }

    // Navigates to mountains page
    public String viewMountain() {
        return "/pages/dynamic/mountains.xhtml?faces-redirect=true";
    }

    // Navigates to weather page
    public String viewWeather() {
        return "/pages/dynamic/weather.xhtml?faces-redirect=true";
    }

    // Navigates to bookings page (requires login)
    public String viewBookings() {

        // Redirect to login if user is not logged in
        if (loginBean.getLoggedInUser() == null) {
            return "/pages/dynamic/login.xhtml?faces-redirect=true";
        }

        return "/pages/dynamic/myBooking.xhtml?faces-redirect=true";
    }

    // Navigates to profile page (requires login)
    public String viewProfile() {

        // Redirect to login if user is not logged in
        if (loginBean.getLoggedInUser() == null) {
            return "/pages/dynamic/login.xhtml?faces-redirect=true";
        }

        return "/pages/dynamic/myProfile.xhtml?faces-redirect=true";
    }

    // Navigates to login page
    public String goToLogin() {
        return "/pages/dynamic/login.xhtml?faces-redirect=true";
    }

    // Navigates to sign up page
    public String goToRegister() {
        return "/pages/dynamic/signUP.xhtml?faces-redirect=true";
    }

    // Logs out the user
    public String logout() {
        return loginBean.logout();
    }
}