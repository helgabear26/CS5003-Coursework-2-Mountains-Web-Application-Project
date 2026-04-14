package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.function.Consumer;
import java.util.function.Function;

public class BaseTemplateDAO {

    protected EntityManagerFactory emf;

    public BaseTemplateDAO() {
        this.emf = Persistence.createEntityManagerFactory("MountainsWebAppDB");
    }

    protected void executeWrite(Consumer<EntityManager> operation) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            operation.accept(em);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    protected <T> T executeRead(Function<EntityManager, T> operation) {
        EntityManager em = emf.createEntityManager();
        try {
            return operation.apply(em);
        } finally {
            em.close();
        }
    }
}