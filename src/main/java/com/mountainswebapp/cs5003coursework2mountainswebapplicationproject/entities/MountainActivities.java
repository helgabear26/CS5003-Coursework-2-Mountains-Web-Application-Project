package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "mountain_activities")
public class MountainActivities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activityID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "activityName", nullable = false)
    private String activityName;

    @Size(max = 255)
    @NotNull
    @Column(name = "category", nullable = false)
    private String category;

    @Size(max = 255)
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "seasonEnd", nullable = false)
    private LocalDate seasonEnd;

    @NotNull
    @Column(name = "seasonStart", nullable = false)
    private LocalDate seasonStart;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mountainID", nullable = false)
    private Mountain mountainID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getSeasonEnd() {
        return seasonEnd;
    }

    public void setSeasonEnd(LocalDate seasonEnd) {
        this.seasonEnd = seasonEnd;
    }

    public LocalDate getSeasonStart() {
        return seasonStart;
    }

    public void setSeasonStart(LocalDate seasonStart) {
        this.seasonStart = seasonStart;
    }

    public Mountain getMountainID() {
        return mountainID;
    }

    public void setMountainID(Mountain mountainID) {
        this.mountainID = mountainID;
    }

}