package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Accommodation;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Mountain;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.MountainActivities;
import jakarta.enterprise.context.*;
import  jakarta.inject.*;

import java.io.Serializable;

@Named("SelectionsBean")
@SessionScoped
public class SelectionsBean implements Serializable {
    private Mountain selectedMountain;
    private Accommodation selectedAccommodation;
    private MountainActivities selectedActivity;

    public Mountain getSelectedMountain() {
        return selectedMountain;
    }
    public void setSelectedMountain(Mountain selectedMountain) {
        this.selectedMountain = selectedMountain;
    }
    public Accommodation getSelectedAccommodation()
        {
        return selectedAccommodation;
        }
    public void setSelectedAccommodation(Accommodation selectedAccommodation) {
        this.selectedAccommodation = selectedAccommodation;
    }
    public MountainActivities getSelectedActivity()
    {
        return selectedActivity;
    }

    public void setSelectedActivity( MountainActivities selectedActivity) {
        this.selectedActivity = selectedActivity;
    }
}
