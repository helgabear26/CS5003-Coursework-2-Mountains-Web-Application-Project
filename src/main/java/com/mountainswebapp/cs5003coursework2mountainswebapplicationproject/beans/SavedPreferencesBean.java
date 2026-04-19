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

    // Serial version UID for Serializable class
    private static final long serialVersionUID = 1L;

    // DAO used to retrieve and manage saved preferences
    @Inject
    private SavedPreferenceDAO savedPreferenceDAO;

    // List of user saved preferences (bookmarks)
    private List<SavedPreference> preferences;

    // Selected preference (for actions like delete)
    private SavedPreference selectedPreference;

    // ID of currently logged-in user
    private Integer userID;

    // Initialises preferences when page loads
    @PostConstruct
    public void init() {
        try {
            // Retrieve session map to get user ID
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap();

            this.userID = (Integer) sessionMap.get("userID");

            // Redirect to login page if user is not logged in
            if (userID == null) {
                redirectTo("pages/webapp/dynamic/login.xhtml");
                return;
            }

            // Load all preferences for user
            this.preferences = savedPreferenceDAO.getPreferencesByUser(userID);

        } catch (Exception e) {
            e.printStackTrace();

            // Show error message if loading fails
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to load bookmarks", null));
        }
    }

    // Loads favourite activities only
    public void loadFavouriteActivities() {
        this.preferences = savedPreferenceDAO.getFavouriteActivity(userID);
    }

    // Loads favourite accommodations only
    public void loadFavouriteAccommodation() {
        this.preferences = savedPreferenceDAO.getFavouriteAccommodation(userID);
    }

    // Loads favourite mountain locations only
    public void loadMountainLocations() {
        this.preferences = savedPreferenceDAO.getFavouriteMountainLocation(userID);
    }

    // Sets selected preference
    public void setSelectedPreference(SavedPreference preference) {
        this.selectedPreference = preference;
    }

    // Deletes a saved preference (bookmark)
    public void deleteBookmark(Integer id) {
        try {
            // Remove preference from database
            savedPreferenceDAO.deleteSavedPreference(id);

            // Refresh preference list
            this.preferences = savedPreferenceDAO.getPreferencesByUser(userID);

            // Show success message
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Preference removed successfully", null));

        } catch (Exception e){
            e.printStackTrace();

            // Show error message if deletion fails
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to delete bookmark", null));
        }
    }

    // Navigates back to home page
    public String goBack() {
        return "/pages/webapp/dynamic/homePage.xhtml?faces-redirect=true";
    }

    // Redirects user to another page
    private void redirectTo(String path) {
        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Returns list of preferences
    public List<SavedPreference> getPreferences() {
        return preferences;
    }

    // Returns selected preference
    public SavedPreference getSelectedPreference() {
        return selectedPreference;
    }
}