package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.SavedPreference;

import java.util.List;

public interface SavedPreferenceDAO {

    void createSavedPreference(SavedPreference bookmarks);
        SavedPreference getSavedPreferenceById(Integer id);
        List<SavedPreference> getAllSavedPreferences();
        List<SavedPreference>getPreferencesByUser(Integer userID);
        List<SavedPreference> getFavouriteMountainLocation(Integer userID);
        List<SavedPreference> getFavouriteActivity(Integer userID);
        List<SavedPreference> getFavouriteAccommodation(Integer userID);

        void updateSavedPreference(SavedPreference bookmarks);

        void deleteSavedPreference(Integer id);

}
