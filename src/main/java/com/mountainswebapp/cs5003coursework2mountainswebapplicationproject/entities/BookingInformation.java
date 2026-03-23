package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "booking_information")
public class BookingInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingInformationID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "accommodationName", nullable = false)
    private String accommodationName;

    @NotNull
    @Column(name = "checkInDate", nullable = false)
    private LocalDate checkInDate;

    @NotNull
    @Column(name = "checkOutDate", nullable = false)
    private LocalDate checkOutDate;

    @NotNull
    @Column(name = "totalPrice", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Size(max = 255)
    @NotNull
    @Column(name = "bookingStatus", nullable = false)
    private String bookingStatus;

    @NotNull
    @Column(name = "noOfGuests", nullable = false)
    private Integer noOfGuests;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bookingID", nullable = false)
    private BookingSystem bookingID;

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

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Integer getNoOfGuests() {
        return noOfGuests;
    }

    public void setNoOfGuests(Integer noOfGuests) {
        this.noOfGuests = noOfGuests;
    }

    public BookingSystem getBookingID() {
        return bookingID;
    }

    public void setBookingID(BookingSystem bookingID) {
        this.bookingID = bookingID;
    }

    public Users getUserID() {
        return usersID;
    }

    public void setUserID(Users usersID) {
        this.usersID = usersID;
    }

}