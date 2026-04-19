package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Users;
import java.util.List;

// DAO interface for Users entity (handles user-related database operations)
public interface UsersDAO {

    // Creates a new user record in the database
    void createUser(Users user);

    // Retrieves a user by their ID
    Users getUserByID(Integer id);

    // Retrieves a user by their username (used for login)
    Users getUserByUsername(String username);

    // Retrieves all users from the database
    List<Users> getAllUsers();

    // Updates an existing user record
    void updateUser(Users user);

    // Deletes a user by their ID
    void deleteUser(Integer id);
}