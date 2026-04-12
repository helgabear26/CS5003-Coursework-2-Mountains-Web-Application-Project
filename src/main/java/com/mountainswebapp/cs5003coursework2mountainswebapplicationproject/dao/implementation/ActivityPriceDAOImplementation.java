package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.implementation;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.ActivityPriceDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.BaseTemplateDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.ActivityPrice;

import java.util.List;

public class ActivityPriceDAOImplementation extends BaseTemplateDAO implements ActivityPriceDAO {
    @Override
    public void createActivityPrice(ActivityPrice activityPrice) {
        executeWrite(em -> em.persist(activityPrice));
    }

    @Override
    public ActivityPrice getActivityByID(Integer id) {
        return executeRead(em -> em.find(ActivityPrice.class, id));
    }

    @Override
    public List<ActivityPrice> getAllActivityPrices() {
        return executeRead(em -> em.createQuery("SELECT ap FROM ActivityPrice ap ORDER BY ap.priceAmount ASC ", ActivityPrice.class)
                .getResultList());
    }

    @Override
    public List<ActivityPrice> getPricesByActivity(Integer activityID) {
        return executeRead(em -> em.createQuery("SELECT ap FROM ActivityPrice ap WHERE ap.activityID.id = :activityID ORDER BY ap.priceAmount ASC", ActivityPrice.class)
                .setParameter("activityID", activityID)
                .getResultList());
    }

    @Override
    public void updateActivityPrice(ActivityPrice activityPrice) {
        executeWrite(em -> em.merge(activityPrice));
    }

    @Override
    public void deleteActivityPrice(Integer id) {
        executeWrite(em -> {
            ActivityPrice activityPrice = em.find(ActivityPrice.class, id);
            if(activityPrice != null) {
                em.remove(activityPrice);
            }
        });
    }
}
