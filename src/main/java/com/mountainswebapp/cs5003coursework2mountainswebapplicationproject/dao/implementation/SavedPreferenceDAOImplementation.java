package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.implementation;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.BaseTemplateDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.SavedPreferenceDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.SavedPreference;

import java.util.List;

public class SavedPreferenceDAOImplementation extends BaseTemplateDAO implements SavedPreferenceDAO {


    @Override
    public void createSavedPreference(SavedPreference bookmarks) {
        executeWrite(em -> em.persist(bookmarks));
    }

    @Override
    public SavedPreference getSavedPreferenceById(Integer id) {
        return executeRead(em -> em.find(SavedPreference.class, id));
    }

    @Override
    public List<SavedPreference> getAllSavedPreferences() {
        return executeRead(em -> em.createQuery("SELECT sp FROM SavedPreference sp", SavedPreference.class)
                .getResultList());
    }

    @Override
    public List<SavedPreference> getPreferencesByUser(Integer userID) {
        return executeRead(em -> em.createQuery("SELECT sp FROM SavedPreference sp WHERE sp.userID.id = :userID", SavedPreference.class)
                .setParameter("userID", userID)
                .getResultList());
    }

    @Override
    public List<SavedPreference> getFavouriteMountainLocation(Integer userID) {
        return executeRead(em -> em.createQuery("SELECT sp FROM SavedPreference sp WHERE sp.userID.id = :userID AND LOWER(sp.itemType) = 'mountain' ", SavedPreference.class)
                .setParameter("userID", userID)
                .getResultList());
    }

    @Override
    public List<SavedPreference> getFavouriteActivity(Integer userID) {
        return executeRead(em -> em.createQuery("SELECT sp FROM SavedPreference sp WHERE sp.userID.id = :userID AND sp.itemType = 'activity'", SavedPreference.class)
                .setParameter("userID", userID)
                .getResultList());
    }

    @Override
    public List<SavedPreference> getFavouriteAccommodation(Integer userID) {
        return executeRead(em -> em.createQuery("SELECT sp FROM SavedPreference sp WHERE sp.userID.id = :userID AND sp.itemType = 'accommodation'" )
                .setParameter("userID", userID)
                .getResultList());
    }

    @Override
    public void updateSavedPreference(SavedPreference bookmarks) {
        executeWrite(em -> em.merge(bookmarks));
    }

    @Override
    public void deleteSavedPreference(Integer id) {
        executeWrite(em -> {
            SavedPreference bookmarks = em.find(SavedPreference.class, id);
                    if(bookmarks != null) {
                        em.remove(bookmarks);
                    }
        });
    }
}
