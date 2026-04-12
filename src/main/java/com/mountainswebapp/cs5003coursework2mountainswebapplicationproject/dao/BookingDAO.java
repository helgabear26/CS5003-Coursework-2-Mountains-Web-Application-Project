package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingDAO {

    void createBooking(Booking booking);

    Booking getBookingByID(Integer id);

    List<Booking> getAllBookings();

    List<Booking> getBookingsByUser (Integer userID);

    List<Booking> getBookingsByAccommodation(Integer accommodationID);

    List<Booking> getBookingsByAccommodationAndDate(Integer accommodationID, LocalDate date);

    long countBookingsByAccommodation(Integer accommodationID);

    long countBookingsByUser(Integer userID);

    void updateBooking(Booking booking);

    void deleteBooking(Integer id);
}
