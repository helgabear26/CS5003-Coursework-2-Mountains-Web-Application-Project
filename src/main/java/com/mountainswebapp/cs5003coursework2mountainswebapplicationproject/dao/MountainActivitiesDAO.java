package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.MountainActivities;

import java.util.List;

// DAO interface for MountainActivities entity (handles activity-related database operations)
public interface MountainActivitiesDAO {

    // Creates a new mountain activity record
    void createMountainActivity(MountainActivities activity);

    // Retrieves a specific activity by its ID
    MountainActivities getActivityByID(Integer id);

    // Retrieves all activities from the database
    List<MountainActivities> getAllActivities();

    // Retrieves all activities associated with a specific mountain
    List<MountainActivities> getActivitiesByMountain(Integer mountainID);

    // Updates an existing activity record
    void updateMountainActivity(MountainActivities activity);

    // Deletes an activity by its ID
    void deleteMountainActivity(Integer id);
}