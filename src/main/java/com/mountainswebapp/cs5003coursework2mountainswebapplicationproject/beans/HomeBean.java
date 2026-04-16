package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.*;
import java.io.Serializable;
@Named("homeBean")
@ViewScoped
public class HomeBean implements Serializable {
    @Inject
    private LoginBean loginBean;

    public boolean isLoggedIn() {
        return loginBean.getLoggedInUser() != null;
    }
    public String  getUsername() {
        if (loginBean.getLoggedInUser() != null) {
            return loginBean.getLoggedInUser().getUsername();
        }
        return "";
    }
    public String viewMountain() {
        return "/pages/dynamic/mountains.xhtml?faces-redirect=true";
    }
    public String viewWeather()
        {
        return "/pages/dynamic/weather.xhtml?faces-redirect=true";
        }
    public String viewBookings()
    {
        if (loginBean.getLoggedInUser() == null) {
            return "/pages/dynamic/login.xhtml?faces-redirect=true";
        }
        return "/pages/dynamic/myBooking.xhtml?faces-redirect=true";
    }
    public  String viewProfile()
    {
        if(loginBean.getLoggedInUser() == null) {
            return "/pages/dynamic/login.xhtml?faces-redirect=true";
        }
        return "/pages/dynamic/myProfile.xhtml?faces-redirect=true";
    }
    public String goToLogin()
    {
        return "/pages/dynamic/login.xhtml?faces-redirect=true";
    }
    public  String goToRegister()
    {
        return "/pages/dynamic/signUP.xhtml?faces-redirect=true";
    }
    public String logout()
    {
        return loginBean.logout();
    }
}
