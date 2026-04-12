package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.implementation;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.AccommodationDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.BaseTemplateDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Accommodation;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class AccommodationDAOImplementation extends BaseTemplateDAO implements AccommodationDAO {


    @Override
    public void createAccommodation(Accommodation accommodation) {
        executeWrite(em -> em.persist(accommodation));
    }

    @Override
    public Accommodation getAccommodationByID(Integer id) {
        return executeRead(em -> em.find(Accommodation.class, id));
    }

    @Override
    public List<Accommodation> getAllAccommodations() {
        return executeRead(em -> em.createQuery("SELECT a FROM Accommodation a ORDER BY a.name").getResultList());
    }

    @Override
    public List<Accommodation> getByMountainID(Integer mountainID) {
        return executeRead(em -> em.createQuery("SELECT a FROM Accommodation a WHERE a.mountainID.id = :mountainID", Accommodation.class)
                .setParameter("mountainID", mountainID)
                .getResultList());
    }

    @Override
    public void updateAccommodation(Accommodation accommodation) {
        executeWrite(em -> em.merge(accommodation));
    }

    @Override
    public void deleteAccommodation(Integer id) {
        executeWrite(em -> {
            Accommodation accommodation = em.find(Accommodation.class, id);
            if (accommodation != null) {
                em.remove(accommodation);
            }
        });

    }
}
