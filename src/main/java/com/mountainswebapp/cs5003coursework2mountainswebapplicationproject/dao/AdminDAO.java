package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Admin;

import java.util.List;

public interface AdminDAO {

    void createAdmin(Admin admin);

    Admin getAdminByID(Integer id);
    Admin validateAdminCredentials(String username, String password);
    List<Admin> getAllAdmins();

    void updateAdmin(Admin admin);

    void deleteAdmin(Integer id);

}
