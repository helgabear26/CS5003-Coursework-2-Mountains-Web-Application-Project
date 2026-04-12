package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "activity_price")
public class ActivityPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priceID", nullable = false)
    private Integer id;

    @Size(max = 10)
    @NotNull
    @Column(name = "currency", nullable = false, length = 10)
    private String currency;

    @NotNull
    @Column(name = "priceAmount", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceAmount;

    @Size(max = 255)
    @NotNull
    @Column(name = "priceType", nullable = false)
    private String priceType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "activityID", nullable = false)
    private MountainActivities activityID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(BigDecimal priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public MountainActivities getActivityID() {
        return activityID;
    }

    public void setActivityID(MountainActivities activityID) {
        this.activityID = activityID;
    }

}