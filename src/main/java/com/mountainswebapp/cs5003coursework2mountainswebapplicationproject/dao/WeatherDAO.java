package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import java.util.Map;

public interface WeatherDAO {

    Map<String, Object> getWeatherByCoordinates(double latitude, double longitude);

    double getTemperature(Map<String, Object> weatherData);
    String getWeatherCondition(Map<String, Object> weatherData);
    double getWindSpeed(Map<String, Object> weatherData);
    double getHumididty(Map<String, Object> weatherData);
    double getUVIndex(Map<String, Object> weatherData);

    boolean hasStormWarning(Map<String, Object> weatherData);
    boolean hasAvalancheRisk(double temperature, double windSpeed);
}
