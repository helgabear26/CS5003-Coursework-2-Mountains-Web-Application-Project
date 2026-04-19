package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.ActivityPriceDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.MountainActivitiesDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.ActivityPrice;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Mountain;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.MountainActivities;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("mountainActivitiesBean")
@ViewScoped
public class MountainActivitiesBean implements Serializable {

    // DAO used to retrieve activities for a mountain
    @Inject
    private MountainActivitiesDAO activitiesDAO;

    // DAO used to retrieve prices for an activity
    @Inject
    private ActivityPriceDAO priceDAO;

    // Bean used to store selected values across pages
    @Inject
    private SelectionsBean selectionsBean;

    // Selected mountain
    private Mountain mountain;

    // List of activities for the selected mountain
    private List<MountainActivities> activities;

    // Activity selected by the user
    private MountainActivities selectedActivity;

    // List of prices for the selected activity
    private List<ActivityPrice> activityPrices;

    // Initialises data when page loads
    @PostConstruct
    public void init() {

        // Get selected mountain from previous page
        mountain = selectionsBean.getSelectedMountain();

        // Load activities if a mountain is selected
        if (mountain != null) {
            activities = activitiesDAO.getActivitiesByMountain(mountain.getId());
        }
    }

    // Handles selecting an activity and loading its prices
    public void selectActivity(MountainActivities activity) {

        // Store selected activity
        selectedActivity = activity;

        // Load prices for selected activity
        activityPrices = priceDAO.getPricesByActivity(activity.getId());
    }

    // Continues booking process after selecting activity
    public String continueBooking() {

        // Ensure an activity has been selected
        if (selectedActivity != null) {

            // Store selected activity in shared bean
            selectionsBean.setSelectedActivity(selectedActivity);

            // Navigate to confirmation page
            return "/pages/dynamic/confirmation.xhtml?faces-redirect=true";
        }

        return null;
    }

    // Navigates back to mountains page
    public String goBack() {
        return "/pages/dynamic/mountains.xhtml?faces-redirect=true";
    }

    // Returns list of activities
    public List<MountainActivities> getActivities() {
        return activities;
    }

    // Returns selected activity
    public MountainActivities getSelectedActivity() {
        return selectedActivity;
    }

    // Returns list of activity prices
    public List<ActivityPrice> getActivityPrices() {
        return activityPrices;
    }

    // Returns selected mountain
    public Mountain getMountain() {
        return mountain;
    }
}