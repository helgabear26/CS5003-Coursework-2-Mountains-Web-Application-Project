package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.implementation;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.AdminDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.BaseTemplateDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Admin;

import java.util.List;

public class AdminDAOImplementation extends BaseTemplateDAO implements AdminDAO {
    @Override
    public void createAdmin(Admin admin) {
        executeWrite(em -> em.persist(admin));
    }

    @Override
    public Admin getAdminByID(Integer id) {
        return executeRead(em -> em.find(Admin.class, id));
    }

    @Override
    public Admin validateAdminCredentials(String email, String firstName) {
        return executeRead(em -> {
            List<Admin> admins = em.createQuery("SELECT a FROM Admin a WHERE a.email = :email AND a.firstName = :firstName", Admin.class)
                    .setParameter("email", email)
                    .setParameter("firstName", firstName)
                    .getResultList();
            return admins.isEmpty() ? null : admins.get(0);
        });
    }

    @Override
    public List<Admin> getAllAdmins() {
        return executeRead(em -> em.createQuery("SELECT a FROM Admin a ORDER BY a.lastName", Admin.class)
                .getResultList());
    }

    @Override
    public void updateAdmin(Admin admin) {
        executeWrite(em -> em.merge(admin));
    }

    @Override
    public void deleteAdmin(Integer id) {
        executeWrite(em -> {
            Admin admin = em.find(Admin.class, id);
            if (admin != null) {
                em.remove(admin);
            }
        });
    }
}
