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
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(BookingBean.class.getName());

    @Inject
    private BookingDAO bookingDAO;

    @Inject
    private LoginBean loginBean;

    @Inject
    private SelectionsBean selectionsBean;

    private Accommodation selectedAccommodation;
    private List<Booking> userBookings;
    private Booking selectedBooking;

    private LocalDate datesBooked;
    private String email;
    private String phoneNo;

    private boolean available;

    @PostConstruct
    public void init() {
        Users user = loginBean.getLoggedInUser();

        if (user != null)
        {
            userBookings = bookingDAO.getBookingsByUser(user.getId());
        }

        selectedAccommodation = selectionsBean.getSelectedAccommodation();
    }

    public void checkAvailability() {
        try {
            if (selectedAccommodation == null || datesBooked == null) {
                addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Select accommodation and date");
                return;
            }


            List<Booking> existing =
                    bookingDAO.getBookingsByAccommodationAndDate(selectedAccommodation.getId(), datesBooked);

            this.available = existing.isEmpty();

            addMessage(
                    FacesMessage.SEVERITY_INFO,
                    available ? "Available" : "Not Available",
                    available ? "This date is available to book" : "Already booked"
            );
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to check availability", e);
        }
    }

    public String createBooking() {
        try {
            Users user = loginBean.getLoggedInUser();

            if (user == null) {
                return "/pages/dynamic/login.xhtml?faces-redirect=true";

            }

            if (selectedAccommodation == null || datesBooked == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Empty or invalid fields"))
                ;
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

            return "/pages/dynamic/myBooking.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void selectBookingtoEdit(Booking booking) {
        this.selectedBooking = booking;
    }

    public void updateBooking() {
        try {
            if (selectedBooking != null) {
                bookingDAO.updateBooking(selectedBooking);

                Users user = loginBean.getLoggedInUser();
                if (user != null)
                {
                    userBookings=bookingDAO.getBookingsByUser(user.getId());
                }
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Booking successful"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteBooking(Integer bookingId) {
        try {
            bookingDAO.deleteBooking(bookingId);

            Users user = loginBean.getLoggedInUser();

            if (user != null) {
                this.userBookings = bookingDAO.getBookingsByUser(user.getId());
            }
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted", "Booking successful removed"));
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