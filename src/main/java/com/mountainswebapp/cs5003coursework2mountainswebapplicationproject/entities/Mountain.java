package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "mountains")
public class Mountain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mountainID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "coordinates", nullable = false)
    private String coordinates;

    @Size(max = 255)
    @NotNull
    @Column(name = "countryRegion", nullable = false)
    private String countryRegion;

    @NotNull
    @Column(name = "difficultyRating", nullable = false)
    private Integer difficultyRating;

    @NotNull
    @Column(name = "elevation", nullable = false)
    private Integer elevation;

    @Size(max = 255)
    @NotNull
    @Column(name = "mountainRange", nullable = false)
    private String mountainRange;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public Integer getDifficultyRating() {
        return difficultyRating;
    }

    public void setDifficultyRating(Integer difficultyRating) {
        this.difficultyRating = difficultyRating;
    }

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public String getMountainRange() {
        return mountainRange;
    }

    public void setMountainRange(String mountainRange) {
        this.mountainRange = mountainRange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}