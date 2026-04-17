package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.UsersDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Users;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.regex.Pattern;

@Named("SignUpBean")
@RequestScoped
public class SignUpBean implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;

    @Inject
    private UsersDAO usersDAO;

    @Inject
    private LoginBean loginBean;

    public String register() {

        FacesContext context = FacesContext.getCurrentInstance();
        boolean missingField = false;

        if (isBlank(email)) {
            context.addMessage("SignupForm:email",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, ""));
            missingField = true;
        }

        if (isBlank(firstName)) {
            context.addMessage("SignupForm:firstname",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, ""));
            missingField = true;
        }

        if (isBlank(lastName)) {
            context.addMessage("SignupForm:lastname",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, ""));
            missingField = true;
        }

        if (isBlank(username)) {
            context.addMessage("SignupForm:username",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, ""));
            missingField = true;
        }

        if (isBlank(password)) {
            context.addMessage("SignupForm:password",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, ""));
            missingField = true;
        }

        if (isBlank(confirmPassword)) {
            context.addMessage("SignupForm:confirmPassword",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null, ""));
            missingField = true;
        }

        if (missingField) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Please fill in all required fields."));
            return null;
        }

        boolean hasError = false;

        if (!isValidEmail(email)) {
            context.addMessage("SignupForm:email",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Please enter a valid email address."));
            hasError = true;
        }

        if (!isValidName(firstName)) {
            context.addMessage("SignupForm:firstname",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "First name cannot contain numbers or symbols."));
            hasError = true;
        }

        if (!isValidName(lastName)) {
            context.addMessage("SignupForm:lastname",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Last name cannot contain numbers or symbols."));
            hasError = true;
        }

        if (password.length() < 6
                || !containsNumber(password)
                || !containsSpecialCharacter(password)) {
            context.addMessage("SignupForm:password",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Password must be at least 6 characters long and include a number and a special character."));
            hasError = true;
        }

        if (!password.equals(confirmPassword)) {
            context.addMessage("SignupForm:confirmPassword",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Passwords do not match."));
            hasError = true;
        }

        if (usersDAO.getUserByUsername(username) != null) {
            context.addMessage("SignupForm:username",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Username is already in use."));
            hasError = true;
        }

        if (hasError) {
            return null;
        }

        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        usersDAO.createUser(user);

        Users savedUser = usersDAO.getUserByUsername(username);
        loginBean.setLoggedInUser(savedUser);

        return "/pages/dynamic/homePage.xhtml?faces-redirect=true";
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }

    private boolean isValidName(String name) {
        return name.matches("^[A-Za-z]+(?:[\\s'-][A-Za-z]+)*$");
    }

    private boolean containsNumber(String password) {
        return password.matches(".*\\d.*");
    }

    private boolean containsSpecialCharacter(String password) {
        return password.matches(".*[^a-zA-Z0-9].*");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}