package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.MountainActivities;

import java.util.List;

public interface MountainActivitiesDAO {

    void createMountainActivity(MountainActivities activity);

    MountainActivities getActivityByID(Integer id);
    List<MountainActivities> getAllActivities();
    List<MountainActivities> getActivitiesByMountain(Integer mountainID);

    void updateMountainActivity(MountainActivities activity);

    void deleteMountainActivity(Integer id);
}
