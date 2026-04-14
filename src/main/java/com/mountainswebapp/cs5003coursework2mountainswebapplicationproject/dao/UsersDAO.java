package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Users;
import java.util.List;

public interface UsersDAO {

    void createUser(Users user);

    Users getUserByID(Integer id);

    Users authenticateUser(String username, String password);

    Users getUserByUsername(String username);

    List<Users> getAllUsers();

    void updateUser(Users user);

    void deleteUser(Integer id);
}