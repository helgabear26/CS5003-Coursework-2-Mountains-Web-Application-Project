package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "booking_system")
public class BookingSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "accommodationName", nullable = false)
    private String accommodationName;

    @Size(max = 255)
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Size(max = 255)
    @NotNull
    @Column(name = "coordinates", nullable = false)
    private String coordinates;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 20)
    @NotNull
    @Column(name = "phoneNumber", nullable = false, length = 20)
    private String phoneNumber;

    @Size(max = 255)
    @NotNull
    @Column(name = "availability", nullable = false)
    private String availability;

    @Size(max = 255)
    @NotNull
    @Column(name = "dates_booked", nullable = false)
    private String datesBooked;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mountainID", nullable = false)
    private Mountain mountainID;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userID", nullable = false)
    private Users usersID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccommodationName() {
        return accommodationName;
    }

    public void setAccommodationName(String accommodationName) {
        this.accommodationName = accommodationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getDatesBooked() {
        return datesBooked;
    }

    public void setDatesBooked(String datesBooked) {
        this.datesBooked = datesBooked;
    }

    public Mountain getMountainID() {
        return mountainID;
    }

    public void setMountainID(Mountain mountainID) {
        this.mountainID = mountainID;
    }

    public Users getUserID() {
        return usersID;
    }

    public void setUserID(Users usersID) {
        this.usersID = usersID;
    }

}