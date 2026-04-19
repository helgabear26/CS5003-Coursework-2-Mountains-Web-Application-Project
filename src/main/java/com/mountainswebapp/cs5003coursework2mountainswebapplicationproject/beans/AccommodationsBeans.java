package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.AccommodationDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Accommodation;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Mountain;
import jakarta.annotation.*;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.*;

import java.io.Serializable;
import java.util.*;

@Named("AccommodationsBeans")
@ViewScoped
public class AccommodationsBeans implements Serializable {

    // DAO used to retrieve accommodation data from the database
    @Inject
    private AccommodationDAO accommodationDAO;

    // Bean used to store selected items across pages
    @Inject
    private SelectionsBean selectionsBean;

    // List of accommodations to display
    private List<Accommodation> accommodations;

    // Selected mountain
    private Mountain mountain;

    // Initialises data when the page loads
    @PostConstruct
    public void init() {

        // Get selected mountain from previous page
        mountain = selectionsBean.getSelectedMountain();

        // If a mountain is selected, load its accommodations
        if (mountain != null) {
            accommodations = accommodationDAO.getByMountainID(mountain.getId());
        }
    }

    // Returns list of accommodations
    public List<Accommodation> getAccommodations() {
        return this.accommodations;
    }

    // Returns selected mountain
    public Mountain getMountain() {
        return mountain;
    }

    // Handles selection of an accommodation
    public String selectAccommodation(Accommodation accommodation) {

        // Store selected accommodation in shared bean
        selectionsBean.setSelectedAccommodation(accommodation);

        // Redirect to next page (activities selection)
        return "/pages/dynamic/activitiesBooking.xhtml?faces-redirect=true";
    }
}