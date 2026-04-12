package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.implementation;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.BaseTemplateDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.MountainDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Mountain;

import java.util.List;

public class MountainDAOImplementation extends BaseTemplateDAO implements MountainDAO {


    @Override
    public void createMountain(Mountain mountain) {
        executeWrite(em -> em.persist(mountain));
    }

    @Override
    public Mountain getMountainByID(Integer id) {
        return executeRead(em -> em.find(Mountain.class, id));
    }

    @Override
    public List<Mountain> getAllMountains() {
            return executeRead(em ->
                    em.createQuery("SELECT m from Mountain m", Mountain.class)
                    .getResultList()
            );
    }

    @Override
    public List<Mountain> searchMountainsByName(String name) {
        return executeRead(em ->
                em.createQuery("SELECT m from Mountain m where LOWER(m.name) LIKE LOWER(:name)", Mountain.class)
                        .setParameter("name", "%" + name + "%")
                        .getResultList()
        );
    }

    @Override
    public List<Mountain> getMountainsByDifficultyRating(String difficultyRating) {
        return executeRead(em -> em.createQuery("SELECT m FROM Mountain m WHERE m.difficultyRating = :difficultyRating", Mountain.class)
                .setParameter("difficultyRating", difficultyRating)
                .getResultList()
        );
    }

    @Override
    public void updateMountain(Mountain mountain) {
        executeWrite(em -> em.merge(mountain));
    }

    @Override
    public void deleteMountain(Integer id) {
        executeWrite(em -> {
            Mountain mountain = em.find(Mountain.class, id);
            if (mountain != null) {
                em.remove(mountain);
            }
        });
    }
}
