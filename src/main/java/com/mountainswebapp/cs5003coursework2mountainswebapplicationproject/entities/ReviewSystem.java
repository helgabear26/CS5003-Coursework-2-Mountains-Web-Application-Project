package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "review_system")
public class ReviewSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "comments_description", nullable = false)
    private String commentsDescription;

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

    public String getCommentsDescription() {
        return commentsDescription;
    }

    public void setCommentsDescription(String commentsDescription) {
        this.commentsDescription = commentsDescription;
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