package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.implementation;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.BaseTemplateDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.UsersDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Users;

import java.util.List;

public class UserDAOImplementation extends BaseTemplateDAO implements UsersDAO {
    @Override
    public void createUser(Users user) {
        executeWrite(em -> em.persist(user));
    }

    @Override
    public Users getUserByID(Integer id) {
        return executeRead(em -> em.find(Users.class, id));
    }

    @Override
    public Users authenticateUser(String username, String password) {
        return executeRead(em -> {List<Users> users = em.createQuery(
                "SELECT u FROM Users u WHERE u.username = :username AND u.password = :password",
                Users.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();
            return users.isEmpty() ? null : users.get(0);
        });
    }

    @Override
    public Users getUserByUsername(String username) {
        return executeRead(em -> {
            List<Users> users = em.createQuery(
                    "SELECT u FROM Users u WHERE u.username = :username", Users.class)
                    .setParameter("username", username)
                    .getResultList();
                    return users.isEmpty() ? null : users.get(0);
        });
    }

    @Override
    public List<Users> getAllUsers() {
        return executeRead(em ->
                em.createQuery("SELECT u FROM Users u", Users.class)
                    .getResultList()
            );
    }

    @Override
    public void updateUser(Users user) {
        executeWrite(em -> em.merge(user));
    }

    @Override
    public void deleteUser(Integer id) {
        executeWrite(em -> {
            Users user = em.find(Users.class, id);
            if (user != null) {
                em.remove(user);
            }
        });
    }
}
