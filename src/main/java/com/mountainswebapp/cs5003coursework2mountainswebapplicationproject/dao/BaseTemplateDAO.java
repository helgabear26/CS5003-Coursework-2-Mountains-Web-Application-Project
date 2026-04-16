package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.function.Consumer;
import java.util.function.Function;
@Transactional
public class BaseTemplateDAO {

    @PersistenceContext
    protected EntityManager em;



    protected void executeWrite(Consumer<EntityManager> operation) {
        operation.accept(em);

    }

    protected <T> T executeRead(Function<EntityManager, T> operation){
        return operation.apply(em);

    }
}
