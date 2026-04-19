package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.ActivityPrice;

import java.util.List;

// DAO interface for ActivityPrice entity (handles database operations for activity pricing)
public interface ActivityPriceDAO {

    // Creates a new activity price record in the database
    void createActivityPrice(ActivityPrice activityPrice);

    // Retrieves a specific activity price by its ID
    ActivityPrice getActivityByID(Integer id);

    // Retrieves all activity prices from the database
    List<ActivityPrice> getAllActivityPrices();

    // Retrieves all prices associated with a specific activity
    List<ActivityPrice> getPricesByActivity(Integer activityID);

    // Updates an existing activity price record
    void updateActivityPrice(ActivityPrice activityPrice);

    // Deletes an activity price by its ID
    void deleteActivityPrice(Integer id);
}