package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.SavedPreferenceDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.SavedPreference;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named("savedPreferencesBean")
@ViewScoped
public class SavedPreferencesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private SavedPreferenceDAO savedPreferenceDAO;

    private List<SavedPreference> preferences;
    private SavedPreference selectedPreference;
    private Integer userID;

    @PostConstruct
    public void init() {
        try {
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap();

            this.userID = (Integer) sessionMap.get("userID");

            if (userID == null) {
                redirectTo("pages/webapp/dynamic/login.xhtml");
                return;
            }

            this.preferences = savedPreferenceDAO.getPreferencesByUser(userID);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to load bookmarks", null));
        }
    }

    public void loadFavouriteActivities() {
        this.preferences = savedPreferenceDAO.getFavouriteActivity(userID);
    }

    public void loadFavouriteAccommodation() {
        this.preferences = savedPreferenceDAO.getFavouriteAccommodation(userID);
    }

    public void loadMountainLocations() {
        this.preferences = savedPreferenceDAO.getFavouriteMountainLocation(userID);
    }

    public void setSelectedPreference(SavedPreference preference) {
        this.selectedPreference = preference;
    }

    public void deleteBookmark(Integer id) {
        try {
            savedPreferenceDAO.deleteSavedPreference(id);
            this.preferences = savedPreferenceDAO.getPreferencesByUser(userID);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Preference removed successfully", null));

        } catch (Exception e){
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to delete bookmark", null));
        }
    }

    public String goBack() {
        return "/pages/webapp/dynamic/homePage.xhtml?faces-redirect=true";
    }

    private void redirectTo(String path) {
        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<SavedPreference> getPreferences() {
        return preferences;
    }

    public SavedPreference getSelectedPreference() {
        return selectedPreference;
    }


}
