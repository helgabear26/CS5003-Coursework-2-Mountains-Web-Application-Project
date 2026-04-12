package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.implementation;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.BaseTemplateDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.BookingDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Booking;
import jakarta.enterprise.context.ApplicationScoped;


import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class BookingDAOImplementation extends BaseTemplateDAO implements BookingDAO {

    @Override
    public void createBooking(Booking booking) {
        executeWrite(em -> {
            booking.setDatesBooked(LocalDate.now());
            em.persist(booking);
        });
    }

    @Override
    public Booking getBookingByID(Integer id) {
        return executeRead(em -> em.find(Booking.class, id));
    }

    @Override
    public List<Booking> getAllBookings() {
        return executeRead(em ->
                em.createQuery("SELECT b FROM Booking b ORDER BY b.datesBooked DESC", Booking.class)
                        .getResultList()
                );
    }

    @Override
    public List<Booking> getBookingsByUser(Integer userID) {
        return executeRead( em -> em.createQuery("SELECT b FROM Booking b WHERE b.userID.id = :userID ORDER BY b.datesBooked DESC",
                Booking.class)
                .setParameter("userID", userID)
                .getResultList()
        );
    }

    @Override
    public List<Booking> getBookingsByAccommodation(Integer accommodationID) {
        return executeRead(em -> em.createQuery("SELECT b FROM Booking b WHERE b.accommodationID.id = :accommodationID",
                Booking.class)
                .setParameter("accommodationID", accommodationID)
                .getResultList()
        );
    }

    @Override
    public List<Booking> getBookingsByAccommodationAndDate(Integer accommodationID, LocalDate date) {
        return executeRead(em -> em.createQuery("SELECT b FROM Booking b WHERE b.accommodationID.id = :accommodationID AND b.datesBooked = :date", Booking.class)
                .setParameter("accommodationID", accommodationID)
                .setParameter("date", date)
                .getResultList()
        );
    }

    @Override
    public long countBookingsByUser(Integer userID) {
        return executeRead(em -> em.createQuery("SELECT COUNT(b) FROM Booking b WHERE b.userID.id = :userID", Long.class)
                .setParameter("userID", userID)
                .getSingleResult()
        );
    }

    @Override
    public long countBookingsByAccommodation(Integer accommodationID) {
        return executeRead(em -> em.createQuery("SELECT COUNT(b) FROM Booking b WHERE b.accommodationID.id = :accommodationID", Long.class)
                .setParameter("accommodationID", accommodationID)
                .getSingleResult());
    }

    @Override
    public void updateBooking(Booking booking) {
        executeWrite(em -> em.merge(booking));
    }

    @Override
    public void deleteBooking(Integer id) {
        executeWrite(em -> { Booking booking = em.find(Booking.class, id);
            if (booking != null) {
                em.remove(booking);
            }


        });

    }
}

