package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Booking;

import java.time.LocalDate;
import java.util.List;

// DAO interface for Booking entity (handles booking-related database operations)
public interface BookingDAO {

    // Creates a new booking record in the database
    void createBooking(Booking booking);

    // Retrieves a booking by its ID
    Booking getBookingByID(Integer id);

    // Retrieves all bookings from the database
    List<Booking> getAllBookings();

    // Retrieves all bookings for a specific user
    List<Booking> getBookingsByUser(Integer userID);

    // Retrieves all bookings for a specific accommodation
    List<Booking> getBookingsByAccommodation(Integer accommodationID);

    // Retrieves bookings for a specific accommodation on a specific date
    List<Booking> getBookingsByAccommodationAndDate(Integer accommodationID, LocalDate date);

    // Counts total bookings for a specific accommodation
    long countBookingsByAccommodation(Integer accommodationID);

    // Counts total bookings made by a specific user
    long countBookingsByUser(Integer userID);

    // Updates an existing booking record
    void updateBooking(Booking booking);

    // Deletes a booking by its ID
    void deleteBooking(Integer id);
}