package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.UsersDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Users;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;
import java.util.regex.Pattern;

@Named("SignUpBean")
@RequestScoped
public class SignUpBean implements Serializable {

    // User input fields
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;

    // DAO used to interact with Users table
    @Inject
    private UsersDAO usersDAO;

    // LoginBean used to automatically log user in after registration
    @Inject
    private LoginBean loginBean;

    // Handles user registration process
    public String register() {

        FacesContext context = FacesContext.getCurrentInstance();
        boolean missingField = false;

        // Check for empty fields
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

        // Stop if any required field is missing
        if (missingField) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Please fill in all required fields."));
            return null;
        }

        boolean hasError = false;

        // Validate email format
        if (!isValidEmail(email)) {
            context.addMessage("SignupForm:email",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Please enter a valid email address."));
            hasError = true;
        }

        // Validate first name
        if (!isValidName(firstName)) {
            context.addMessage("SignupForm:firstname",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "First name cannot contain numbers or symbols."));
            hasError = true;
        }

        // Validate last name
        if (!isValidName(lastName)) {
            context.addMessage("SignupForm:lastname",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Last name cannot contain numbers or symbols."));
            hasError = true;
        }

        // Validate password strength
        if (password.length() < 6
                || !containsNumber(password)
                || !containsSpecialCharacter(password)) {
            context.addMessage("SignupForm:password",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Password must be at least 6 characters long and include a number and a special character."));
            hasError = true;
        }

        // Check password confirmation
        if (!password.equals(confirmPassword)) {
            context.addMessage("SignupForm:confirmPassword",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Passwords do not match."));
            hasError = true;
        }

        // Check if username already exists
        if (usersDAO.getUserByUsername(username) != null) {
            context.addMessage("SignupForm:username",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Username is already in use."));
            hasError = true;
        }

        // Stop if validation fails
        if (hasError) {
            return null;
        }

        // Hash password using BCrypt for security
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Create new user object
        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(hashedPassword);

        // Save user to database
        usersDAO.createUser(user);

        // Retrieve saved user and log them in
        Users savedUser = usersDAO.getUserByUsername(username);
        loginBean.setLoggedInUser(savedUser);

        // Redirect to home page after successful registration
        return "/pages/dynamic/homePage.xhtml?faces-redirect=true";
    }

    // Checks if a field is empty
    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    // Validates email format using regex
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }

    // Validates name (letters only, with optional spaces or hyphens)
    private boolean isValidName(String name) {
        return name.matches("^[A-Za-z]+(?:[\\s'-][A-Za-z]+)*$");
    }

    // Checks if password contains a number
    private boolean containsNumber(String password) {
        return password.matches(".*\\d.*");
    }

    // Checks if password contains a special character
    private boolean containsSpecialCharacter(String password) {
        return password.matches(".*[^a-zA-Z0-9].*");
    }

    // Getters and setters

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