package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Mountain;

import java.util.List;

// DAO interface for Mountain entity (handles mountain-related database operations)
public interface MountainDAO {

    // Creates a new mountain record in the database
    void createMountain(Mountain mountain);

    // Retrieves a mountain by its ID
    Mountain getMountainByID(Integer id);

    // Retrieves all mountains from the database
    List<Mountain> getAllMountains();

    // Searches for mountains by name (partial or full match)
    List<Mountain> searchMountainsByName(String name);

    // Retrieves mountains based on difficulty rating
    List<Mountain> getMountainsByDifficultyRating(String difficultyRating);

    // Updates an existing mountain record
    void updateMountain(Mountain mountain);

    // Deletes a mountain by its ID
    void deleteMountain(Integer id);
}