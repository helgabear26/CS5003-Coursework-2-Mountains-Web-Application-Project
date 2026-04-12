package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.AccommodationDAO;
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

@Named("accommodationBean")
@ViewScoped
public class AccommodationBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(AccommodationBean.class.getName());

    @Inject
    private AccommodationDAO accommodationDAO;

    private Mountain selectedMountain;
    private List<Accommodation> accommodations = Collections.emptyList();
    private Accommodation selectedAccommodation;

    @PostConstruct
    public void init() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();

            if (context == null)
                return;

            selectedMountain = (Mountain) context.getExternalContext()
                    .getSessionMap()
                    .get("SelectedMountain");

            if (selectedMountain == null) {
                returnTo("/pages/dynamic/mountains.xhtml");
                return;
            }

            accommodations = accommodationDAO.getByMountainID(selectedMountain.getId());

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to load accommodation", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to load accommodations");
        }
    }

    public String selectAccommodation(Accommodation accommodation) {
        try {
            if (accommodation == null) {
                addMessage(FacesMessage.SEVERITY_WARN, "Warning", "Please select an accommodation");
                return null;
            }

            this.selectedAccommodation = accommodation;

            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap()
                    .put("SelectedAccommodation", accommodation);

            return "/pages/booking/booking.xhtml?faces-redirect=true"; // remember to create this page

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Selection failed", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to select accommodation");
            return null;
        }
    }

    public String goBack() {
        return "/pages/dynamic/mountains.xhtml?faces-redirect=true";
    }

     private void returnTo(String path) {
         try {
             FacesContext.getCurrentInstance()
                     .getExternalContext()
                     .redirect(path);
         } catch (Exception e) {
             LOGGER.log(Level.WARNING, "Redirect failed: " + path, e);
         }
     }

     private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
     }

     public List<Accommodation> getAccommodations() {
        return accommodations;
     }

    public Accommodation getSelectedAccommodation() {
        return this.selectedAccommodation;
    }

    public Mountain getSelectedMountain() {
        return selectedMountain;
    }
}
