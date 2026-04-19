package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.function.Consumer;
import java.util.function.Function;

@Transactional
// Base DAO class that provides reusable database operations
public class BaseTemplateDAO {

    // EntityManager used to interact with the database
    @PersistenceContext
    protected EntityManager em;

    // Executes write operations (INSERT, UPDATE, DELETE)
    protected void executeWrite(Consumer<EntityManager> operation) {
        operation.accept(em);
    }

    // Executes read operations (SELECT queries)
    protected <T> T executeRead(Function<EntityManager, T> operation){
        return operation.apply(em);
    }
}