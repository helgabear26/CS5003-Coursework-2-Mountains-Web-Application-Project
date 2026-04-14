package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.AccommodationDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.MountainDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Accommodation;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Mountain;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("mountainListBean")
public class mountainListBean implements Serializable {
    @Inject
    private MountainDAO mountainDAO;
    private List<Mountain>Mountains;

    @PostConstruct
    public void init()
    {
        Mountains = mountainDAO.getAllMountains();
    }
    public List<Mountain> getMountains()
    {
        return Mountains;
    }
    public String selectMountain(Mountain mountain) {
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .put("selectedmountain", mountain);
        return "/webapp/pages/dynamic/mountains.xhtml?faces-redirect=true";
    }
}
