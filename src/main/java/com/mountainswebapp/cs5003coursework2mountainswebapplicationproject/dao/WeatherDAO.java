package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import java.util.Map;

// DAO interface for Weather data (handles external weather API data processing)
public interface WeatherDAO {

    // Retrieves raw weather data based on latitude and longitude
    Map<String, Object> getWeatherByCoordinates(double latitude, double longitude);

    // Extracts temperature from weather data
    double getTemperature(Map<String, Object> weatherData);

    // Extracts weather condition (e.g. sunny, rainy) from weather data
    String getWeatherCondition(Map<String, Object> weatherData);

    // Extracts wind speed from weather data
    double getWindSpeed(Map<String, Object> weatherData);

    // Extracts humidity level from weather data
    double getHumididty(Map<String, Object> weatherData);

    // Extracts UV index from weather data
    double getUVIndex(Map<String, Object> weatherData);

    // Checks if there is a storm warning in the weather data
    boolean hasStormWarning(Map<String, Object> weatherData);

    // Determines avalanche risk based on temperature and wind speed
    boolean hasAvalancheRisk(double temperature, double windSpeed);
}