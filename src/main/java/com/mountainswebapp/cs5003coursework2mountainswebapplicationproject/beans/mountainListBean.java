package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.MountainDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Mountain;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("mountainListBean")
@ViewScoped
public class mountainListBean implements Serializable {

    // DAO used to retrieve mountain data from database
    @Inject
    private MountainDAO mountainDAO;

    // Bean used to store selected items across pages
    @Inject
    private SelectionsBean selectionsBean;

    // List of mountains to display
    private List<Mountain> mountains;

    // Initialises mountain list when page loads
    @PostConstruct
    public void init() {

        // Retrieve all mountains from database
        mountains = mountainDAO.getAllMountains();
    }

    // Returns list of mountains
    public List<Mountain> getMountains() {
        return mountains;
    }

    // Handles selecting a mountain
    public String selectMountain(Mountain mountain) {

        // Store selected mountain
        selectionsBean.setSelectedMountain(mountain);

        // Reset previous selections to avoid incorrect data
        selectionsBean.setSelectedAccommodation(null);
        selectionsBean.setSelectedActivity(null);

        // Navigate to accommodation (hut booking) page
        return "/pages/dynamic/hutBooking.xhtml?faces-redirect=true";
    }

    // Navigates back to home page
    public String goBack() {
        return "/pages/dynamic/homePage.xhtml?faces-redirect=true";
    }
}