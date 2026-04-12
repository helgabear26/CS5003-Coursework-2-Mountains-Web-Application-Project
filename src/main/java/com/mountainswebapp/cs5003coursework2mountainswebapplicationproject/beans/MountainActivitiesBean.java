package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;


import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.ActivityPriceDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.MountainActivitiesDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.ActivityPrice;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Mountain;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.MountainActivities;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Named("mountainActivitiesBean")
@ViewScoped
public class MountainActivitiesBean implements Serializable {
    private static final long serialVersionUID =1L;
    private static final Logger LOGGER = Logger.getLogger(MountainActivitiesBean.class.getName());

    @Inject
    private MountainActivitiesDAO activitiesDAO;

    @Inject
    private ActivityPriceDAO priceDAO;

    private Mountain mountain;
    private List<MountainActivities> activities;
    private MountainActivities selectedActivity;
    private List<ActivityPrice> activityPrices = Collections.emptyList();

    @PostConstruct
    public void init() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);

            if (session != null) {
                returnTo ("/pages/dynamic/mountains.xhtml");
               return;
            }
            this.mountain = (Mountain) session.getAttribute("selectedMountain");

            if (mountain == null) {
                returnTo ("/pages/dynamic/mountains.xhtml");
                return;
            }

            this.activities = activitiesDAO.getActivitiesByMountain(mountain.getId());
        } catch (Exception e) {
           LOGGER.log(Level.SEVERE, "Failed to load activities", e);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to load activities", null));
        }
    }

    public void selectActivity(MountainActivities activity) {
        this.selectedActivity = activity;
        this.activityPrices = (activity != null)
                ? priceDAO.getPricesByActivity(activity.getId())
                : Collections.emptyList();
    }

    public String bookActivity() {
        if (selectedActivity == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Please select an activity.", null));
            return null;
        }
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session == null)
                return null;

            session.setAttribute("selectedACtivity", selectedActivity);
            return "pages/dynamic/huntBooking.xhtml?faces-redirect=true";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Booking attempt failed ", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Booking attempt failed. Please Try again. ", null));
            return null;
        }
    }

    private void returnTo(String path){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(path);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Redirect failed: " + path, e);
        }
    }

    public String goBack() {
        return "/pages/dynamic/mountains.xhtml?faces-redirect=true";
    }

    public List<MountainActivities> getActivities() {
        return activities;
    }

    public MountainActivities getSelectedActivity() {
        return selectedActivity;
    }

    public List<ActivityPrice> getActivityPrices(){
        return activityPrices;
    }

    public Mountain getMountain() {
        return mountain;
    }

}


