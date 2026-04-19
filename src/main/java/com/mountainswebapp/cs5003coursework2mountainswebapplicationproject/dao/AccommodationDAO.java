package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Accommodation;

import java.util.List;

// DAO interface for Accommodation entity (defines database operations)
public interface AccommodationDAO {

    // Creates a new accommodation record in the database
    void createAccommodation(Accommodation accommodation);

    // Retrieves a single accommodation by its ID
    Accommodation getAccommodationByID(Integer id);

    // Retrieves all accommodations from the database
    List<Accommodation> getAllAccommodations();

    // Retrieves accommodations associated with a specific mountain
    List<Accommodation> getByMountainID(Integer mountainId);

    // Updates an existing accommodation record
    void updateAccommodation(Accommodation accommodation);

    // Deletes an accommodation by its ID
    void deleteAccommodation(Integer id);
}