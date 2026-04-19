package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.BookingDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Accommodation;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Booking;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Users;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("bookingBean")
@ViewScoped
public class BookingBean implements Serializable {

    // Serial version UID for Serializable class
    private static final long serialVersionUID = 1L;

    // Logger used for recording errors and system events
    private static final Logger LOGGER = Logger.getLogger(BookingBean.class.getName());

    // DAO used to handle booking database operations
    @Inject
    private BookingDAO bookingDAO;

    // Bean used to get the currently logged-in user
    @Inject
    private LoginBean loginBean;

    // Bean used to store selected mountain, accommodation, and activity
    @Inject
    private SelectionsBean selectionsBean;

    // Currently selected accommodation
    private Accommodation selectedAccommodation;

    // List of bookings for the logged-in user
    private List<Booking> userBookings;

    // Booking selected for editing
    private Booking selectedBooking;

    // Booking form fields
    private LocalDate datesBooked;
    private String email;
    private String phoneNo;

    // Stores whether selected accommodation is available
    private boolean available;

    // Initialises bookings and selected accommodation when page loads
    @PostConstruct
    public void init() {
        Users user = loginBean.getLoggedInUser();

        // Load bookings for logged-in user
        if (user != null) {
            userBookings = bookingDAO.getBookingsByUser(user.getId());
        }

        // Get selected accommodation from previous step
        selectedAccommodation = selectionsBean.getSelectedAccommodation();
    }

    // Checks whether selected accommodation is available for the chosen date
    public void checkAvailability() {
        try {
            // Ensure accommodation and date are selected
            if (selectedAccommodation == null || datesBooked == null) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Select accommodation and date");
                return;
            }

            // Retrieve any existing bookings for that accommodation and date
            List<Booking> existing =
                    bookingDAO.getBookingsByAccommodationAndDate(selectedAccommodation.getId(), datesBooked);

            // Accommodation is available if no bookings already exist
            this.available = existing.isEmpty();

            // Display result message
            addMessage(
                    FacesMessage.SEVERITY_INFO,
                    available ? "Available" : "Not Available",
                    available ? "This date is available to book" : "Already booked"
            );
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to check availability", e);
        }
    }

    // Creates a new booking
    public String createBooking() {
        try {
            Users user = loginBean.getLoggedInUser();

            // Redirect to login page if user is not logged in
            if (user == null) {
                return "/pages/dynamic/login.xhtml?faces-redirect=true";
            }

            // Validate required fields
            if (selectedAccommodation == null || datesBooked == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Empty or invalid fields"));
                return null;
            }

            // Prevent duplicate booking for same accommodation and date
            if (!bookingDAO.getBookingsByAccommodationAndDate(selectedAccommodation.getId(), datesBooked).isEmpty()) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Already booked");
                return null;
            }

            // Create booking object and set details
            Booking booking = new Booking();
            booking.setAccommodationID(selectedAccommodation);
            booking.setUserID(user);
            booking.setDatesBooked(datesBooked);
            booking.setEmail(email);
            booking.setPhoneNo(phoneNo);

            // Save booking to database
            bookingDAO.createBooking(booking);

            // Refresh list of user bookings
            this.userBookings = bookingDAO.getBookingsByUser(user.getId());

            // Clear form fields after successful booking
            clearForm();

            // Show success message
            addMessage(FacesMessage.SEVERITY_INFO, "Success", "Booking successful");

            // Redirect to bookings page
            return "/pages/dynamic/myBooking.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Selects a booking for editing
    public void selectBookingtoEdit(Booking booking) {
        this.selectedBooking = booking;
    }

    // Updates selected booking details
    public void updateBooking() {
        try {
            if (selectedBooking != null) {
                // Update booking in database
                bookingDAO.updateBooking(selectedBooking);

                // Refresh booking list for logged-in user
                Users user = loginBean.getLoggedInUser();
                if (user != null) {
                    userBookings = bookingDAO.getBookingsByUser(user.getId());
                }

                // Show success message
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Booking successful"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Deletes a booking by ID
    public void deleteBooking(Integer bookingId) {
        try {
            // Remove booking from database
            bookingDAO.deleteBooking(bookingId);

            // Refresh booking list for logged-in user
            Users user = loginBean.getLoggedInUser();
            if (user != null) {
                this.userBookings = bookingDAO.getBookingsByUser(user.getId());
            }

            // Show success message
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted", "Booking successful removed"));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to delete", e);
        }
    }

    // Redirects to another page
    private void returnTo(String path) {
        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect(path);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Redirect failed: " + path, e);
        }
    }

    // Adds a JSF message to the page
    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    // Clears booking form fields
    private void clearForm() {
        datesBooked = null;
        email = null;
        phoneNo = null;
        selectedAccommodation = null;
        available = false;
    }

    // Returns selected booking date
    public LocalDate getDatesBooked() {
        return datesBooked;
    }

    // Sets booking date
    public void setDatesBooked(LocalDate datesBooked) {
        this.datesBooked = datesBooked;
    }

    // Returns email
    public String getEmail() {
        return email;
    }

    // Sets email
    public void setEmail(String email) {
        this.email = email;
    }

    // Returns phone number
    public String getPhoneNo() {
        return phoneNo;
    }

    // Sets phone number
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    // Returns selected accommodation
    public Accommodation getSelectedAccommodation() {
        return selectedAccommodation;
    }

    // Sets selected accommodation
    public void setSelectedAccommodation(Accommodation selectedAccommodation) {
        this.selectedAccommodation = selectedAccommodation;
    }

    // Returns list of user bookings
    public List<Booking> getUserBookings() {
        return userBookings;
    }

    // Returns availability status
    public boolean isAvailable() {
        return available;
    }

    // Returns selected booking
    public Booking getSelectedBooking() {
        return selectedBooking;
    }

    // Sets selected booking
    public void setSelectedBooking(Booking selectedBooking) {
        this.selectedBooking = selectedBooking;
    }
}