package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.AccommodationDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Accommodation;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Mountain;
import  jakarta.annotation.*;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.*;

import  java.io.Serializable;
import  java.util.*;

@Named("AccommodationsBeans")
@ViewScoped
public class AccommodationsBeans implements Serializable {
    @Inject
    private AccommodationDAO accommodationDAO;

    @Inject
    private SelectionsBean selectionsBean;
    private List<Accommodation> accommodations;
    private Mountain mountain;

    @PostConstruct
    public void init() {
        mountain = selectionsBean.getSelectedMountain();

        if (mountain != null) {
            accommodations=accommodationDAO.getByMountainID(mountain.getId());
        }
    }

    public List<Accommodation> getAccommodations() {
        return this.accommodations;
    }
    public Mountain getMountain() {
        return mountain;
    }
    public String selectAccommodation(Accommodation accommodation) {
        selectionsBean.setSelectedAccommodation(accommodation);
        return "/pages/dynamic/activitiesBooking.xhtml?faces-redirect=true";
    }
}

