package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.implementation;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.BaseTemplateDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.MountainActivitiesDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.MountainActivities;

import java.util.List;

public class MountainActivitiesDAOImplementation extends BaseTemplateDAO implements MountainActivitiesDAO {

    @Override
    public void createMountainActivity(MountainActivities activity) {
        executeWrite(em -> em.persist(activity));
    }

    @Override
    public MountainActivities getActivityByID(Integer id) {
        return executeRead(em -> em.find(MountainActivities.class, id));
    }

    @Override
    public List<MountainActivities> getAllActivities() {
        return executeRead(em -> em.createQuery("SELECT ma FROM MountainActivities ma ORDER BY ma.activityName", MountainActivities.class)
                .getResultList());
    }

    @Override
    public List<MountainActivities> getActivitiesByMountain(Integer mountainID) {
        return executeRead(em -> em.createQuery("SELECT ma FROM MountainActivities  ma WHERE ma.mountainID.id = :mountainID ORDER BY ma.activityName", MountainActivities.class)
                .setParameter("mountainID", mountainID)
                .getResultList());
    }

    @Override
    public void updateMountainActivity(MountainActivities activity) {
        executeWrite(em -> em.merge(activity));
    }

    @Override
    public void deleteMountainActivity(Integer id) {
        executeWrite(em -> {
            MountainActivities activity = em.find(MountainActivities.class, id);
            if (activity != null) {
                em.remove(activity);
            }
        });
    }
}

