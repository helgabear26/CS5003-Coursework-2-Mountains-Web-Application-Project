package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Accommodation;

import java.util.List;

public interface AccommodationDAO {

    void createAccommodation(Accommodation accommodation);

    Accommodation getAccommodationByID(Integer id);


    List<Accommodation> getAllAccommodations();

    List<Accommodation> getByMountainID(Integer mountainId);

    void updateAccommodation(Accommodation accommodation);

    void deleteAccommodation(Integer id);
}
