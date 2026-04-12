package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.MountainActivitiesDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Mountain;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.MountainActivities;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named("mountainInformationBean")
@ViewScoped
public class MountainInformationBean implements Serializable {
    private static final long serialVersionUID = 1L;


    @Inject
    private MountainActivitiesDAO activitiesDAO;

    private Mountain mountain;
    private List<MountainActivities> activities;

    @PostConstruct
    public void init(){
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        this.mountain = (Mountain) sessionMap.get("selectedMountain");
        sessionMap.remove("selectedMountain");

        if (mountain == null) {
            redirectTo ("/pages/webapp/dynamic/homePage.xhtml");
            return;
        }

        this.activities = activitiesDAO.getActivitiesByMountain(mountain.getId());
    }

    private void redirectTo(String path) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String viewActivities() {
        return "/pages/webapp/dynamic/activitiesBooking.xhtml?faces-redirect=true";
    }

    public String viewWeather() {
        return "/pages/webapp/dynamic/weather.xhtml?faces-redirect=true";
    }

    public String viewBooking() {
        return "/pages/webapp/dynamic/hutBooking.xhtml?faces-redirect=true";  //change this to bookingSystem when that page is created
    }

    public String goBack() {
        return "/pages/webapp/dynamic/homePage.xhtml?faces-redirect=true";
    }

    public Mountain getMountain() {
        return mountain;
    }

    public List<MountainActivities> getActivities() {
        return activities;
    }

}
