package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.SavedPreference;

import java.util.List;

// DAO interface for SavedPreference entity (handles user bookmarks and preferences)
public interface SavedPreferenceDAO {

    // Creates a new saved preference (bookmark)
    void createSavedPreference(SavedPreference bookmarks);

    // Retrieves a saved preference by its ID
    SavedPreference getSavedPreferenceById(Integer id);

    // Retrieves all saved preferences
    List<SavedPreference> getAllSavedPreferences();

    // Retrieves all preferences for a specific user
    List<SavedPreference> getPreferencesByUser(Integer userID);

    // Retrieves favourite mountain locations for a user
    List<SavedPreference> getFavouriteMountainLocation(Integer userID);

    // Retrieves favourite activities for a user
    List<SavedPreference> getFavouriteActivity(Integer userID);

    // Retrieves favourite accommodations for a user
    List<SavedPreference> getFavouriteAccommodation(Integer userID);

    // Updates an existing saved preference
    void updateSavedPreference(SavedPreference bookmarks);

    // Deletes a saved preference by its ID
    void deleteSavedPreference(Integer id);
}