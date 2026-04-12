package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.implementation;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.WeatherDAO;

import java.util.Map;

public class WeatherDAOImplementation implements WeatherDAO {

    private static final String API_KEY = ""
    private static final String API_URL = "";

    @Override
    public Map<String, Object> getWeatherByCoordinates(double latitude, double longitude) {
        return Map.of();
    }

    @Override
    public double getTemperature(Map<String, Object> weatherData) {
        return 0;
    }

    @Override
    public String getWeatherCondition(Map<String, Object> weatherData) {
        return "";
    }

    @Override
    public double getWindSpeed(Map<String, Object> weatherData) {
        return 0;
    }

    @Override
    public double getHumididty(Map<String, Object> weatherData) {
        return 0;
    }

    @Override
    public double getUVIndex(Map<String, Object> weatherData) {
        return 0;
    }

    @Override
    public boolean hasStormWarning(Map<String, Object> weatherData) {
        return false;
    }

    @Override
    public boolean hasAvalancheRisk(double temperature, double windSpeed) {
        return false;
    }
}
