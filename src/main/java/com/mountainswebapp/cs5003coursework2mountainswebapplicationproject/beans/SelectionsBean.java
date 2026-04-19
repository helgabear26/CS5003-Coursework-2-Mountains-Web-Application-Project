package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Accommodation;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Mountain;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.MountainActivities;
import jakarta.enterprise.context.*;
import jakarta.inject.*;

import java.io.Serializable;

@Named("SelectionsBean")
@SessionScoped
public class SelectionsBean implements Serializable {

    // Stores selected mountain across pages
    private Mountain selectedMountain;

    // Stores selected accommodation across pages
    private Accommodation selectedAccommodation;

    // Stores selected activity across pages
    private MountainActivities selectedActivity;

    // Returns selected mountain
    public Mountain getSelectedMountain() {
        return selectedMountain;
    }

    // Sets selected mountain
    public void setSelectedMountain(Mountain selectedMountain) {
        this.selectedMountain = selectedMountain;
    }

    // Returns selected accommodation
    public Accommodation getSelectedAccommodation() {
        return selectedAccommodation;
    }

    // Sets selected accommodation
    public void setSelectedAccommodation(Accommodation selectedAccommodation) {
        this.selectedAccommodation = selectedAccommodation;
    }

    // Returns selected activity
    public MountainActivities getSelectedActivity() {
        return selectedActivity;
    }

    // Sets selected activity
    public void setSelectedActivity(MountainActivities selectedActivity) {
        this.selectedActivity = selectedActivity;
    }
}