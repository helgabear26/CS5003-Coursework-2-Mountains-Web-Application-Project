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
    @Inject
    private MountainDAO mountainDAO;

    @Inject
    private SelectionsBean selectionsBean;

    private List<Mountain> mountains;

    @PostConstruct
    public void init()
    {mountains= mountainDAO.getAllMountains();
    }
    public List<Mountain> getMountains()
    {
        return mountains;
    }
    public String selectMountain(Mountain mountain) {
        selectionsBean.setSelectedMountain(mountain);
        selectionsBean.setSelectedAccommodation(null);
        selectionsBean.setSelectedActivity(null);
        return "/pages/dynamic/hutBooking.xhtml?faces-redirect=true";
    }

    public String goBack() {
        return "/pages/dynamic/homePage.xhtml?faces-redirect=true";
    }




}
