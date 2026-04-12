package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.ActivityPrice;

import java.util.List;

public interface ActivityPriceDAO {

    void createActivityPrice(ActivityPrice activityPrice);

    ActivityPrice getActivityByID(Integer id);
    List<ActivityPrice> getAllActivityPrices();
    List<ActivityPrice> getPricesByActivity(Integer activityID);

    void updateActivityPrice(ActivityPrice activityPrice);

    void deleteActivityPrice(Integer id);
}
