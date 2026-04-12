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

import java.awt.print.Book;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("bookingBean")
@ViewScoped
public class BookingBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(BookingBean.class.getName());

    @Inject
    private BookingDAO bookingDAO;

    private Accommodation selectedAccommodation;
    private List<Booking> userBookings;
    private Booking selectedBooking;

    private LocalDate datesBooked;
    private String email;
    private String phoneNo;

    private boolean available;

    @PostConstruct
    public void init() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();

            if (context == null) return;

            Users user = (Users) context.getExternalContext()
                    .getSessionMap()
                    .get("loggedUser");

            if (user == null) {
                returnTo("/pages/dynamic/login.xhtml");
                return;
            }
            this.userBookings = bookingDAO.getBookingsByUser(user.getId());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to load bookings", e);
        }
    }

    public void checkAvailability() {
        try {
            if (selectedAccommodation == null || datesBooked == null) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Select accommodation and date");
                return;
            }

            List<Booking> existing = bookingDAO.getBookingsByAccommodationAndDate(selectedAccommodation.getId(), datesBooked);

            this.available = existing.isEmpty();

            addMessage(FacesMessage.SEVERITY_INFO, available ? "Available" : "Not Available", available ? "This date is available to book" : "Already booked");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to check availability", e);
        }
    }

    public String createBooking() {
        try {
            Users user = getLoggedInUser();

            if (user == null) {
                returnTo("/pages/dynamic/login.xhtml");
                return null;
            }

            if (selectedAccommodation == null || datesBooked == null) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Empty or invalid fields");
                return null;
            }

            if (!bookingDAO.getBookingsByAccommodationAndDate(selectedAccommodation.getId(), datesBooked).isEmpty()) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Already booked");
                return null;
            }

            Booking booking = new Booking();
            booking.setAccommodationID(selectedAccommodation);
            booking.setUserID(user);
            booking.setDatesBooked(datesBooked);
            booking.setEmail(email);
            booking.setPhoneNo(phoneNo);

            bookingDAO.createBooking(booking);

            this.userBookings = bookingDAO.getBookingsByUser(user.getId());

            clearForm();

            addMessage(FacesMessage.SEVERITY_INFO, "Success", "Booking successful");

            return "/pages/booking/confirmation.xhtml?faces-redirect=true"; // add this to the static pages
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Booking failed", e);
            return null;
        }
    }

    public void updateBooking(Booking booking) {
        try {
            if (booking == null)
                return;
            bookingDAO.updateBooking(booking);

            Users user = getLoggedInUser();

            if (user != null) {
                this.userBookings = bookingDAO.getBookingsByUser(user.getId());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Update failed", e);
        }
    }

    public void deleteBooking(Integer bookingId) {
        try {
            bookingDAO.deleteBooking(bookingId);

            Users user = getLoggedInUser();

            if (user != null) {
                this.userBookings = bookingDAO.getBookingsByUser(user.getId());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to delete", e);
        }
    }

    private void returnTo(String path) {
        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect(path);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Redirect failed: " + path, e);
        }
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    private void clearForm() {
        datesBooked = null;
        email = null;
        phoneNo = null;
        selectedAccommodation = null;
        available = false;
    }

    public LocalDate getDatesBooked() {
        return datesBooked;
    }

    public void setDatesBooked(LocalDate datesBooked) {
        this.datesBooked = datesBooked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Accommodation getSelectedAccommodation() {
        return selectedAccommodation;
    }

    public void setSelectedAccommodation(Accommodation selectedAccommodation) {
        this.selectedAccommodation = selectedAccommodation;
    }

    public List<Booking> getUserBookings() {
        return userBookings;
    }

    public boolean isAvailable() {
        return available;
    }

    public Booking getSelectedBooking() {
        return selectedBooking;
    }

    public void setSelectedBooking(Booking selectedBooking) {
        this.selectedBooking = selectedBooking;
    }
}