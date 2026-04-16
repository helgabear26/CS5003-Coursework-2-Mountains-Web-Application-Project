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

    @Inject
    private MountainActivitiesDAO activitiesDAO;

    @Inject
    private ActivityPriceDAO priceDAO;

    @Inject
    private SelectionsBean selectionsBean;


    private Mountain mountain;
    private List<MountainActivities> activities;
    private MountainActivities selectedActivity;
    private List<ActivityPrice> activityPrices;

    @PostConstruct
    public void init() {
        mountain = selectionsBean.getSelectedMountain();

        if (mountain != null) {
            activities = activitiesDAO.getActivitiesByMountain(mountain.getId());

        }
    }

    public void selectActivity(MountainActivities activity) {
        selectedActivity = activity;
        activityPrices = priceDAO.getPricesByActivity(activity.getId());
    }
    public String continueBooking(){
        if(selectedActivity != null){
            selectionsBean.setSelectedActivity(selectedActivity);
            return "/pages/dynamic/confirmation.xhtml?faces-redirect=true";
        }
        return null;
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


