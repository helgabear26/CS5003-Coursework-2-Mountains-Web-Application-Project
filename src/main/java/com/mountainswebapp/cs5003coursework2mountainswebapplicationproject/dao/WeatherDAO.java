package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Mountain;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.model.WeatherData;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
 * WeatherDAO interface
 * Handles retrieval of weather data for mountains using coordinates or mountain objects
 */
public interface WeatherDAO {

    /*
     * Gets weather data using latitude and longitude
     * Returns Optional in case no data is found or an error occurs
     */
    Optional<WeatherData> getWeather(double latitude, double longitude);

    /*
     * Gets weather data using a Mountain object
     * Extracts coordinates from the mountain and retrieves weather
     */
    Optional<WeatherData> getWeather(Mountain mountain);

    /*
     * Gets weather data for a list of mountains
     * Returns a map where:
     * Key = Mountain ID
     * Value = WeatherData for that mountain
     */
    Map<Integer, WeatherData> getWeatherForMountains(List<Mountain> mountains);
}