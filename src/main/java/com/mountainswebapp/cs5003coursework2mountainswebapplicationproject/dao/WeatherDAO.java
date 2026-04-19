package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Mountain;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.model.WeatherData;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WeatherDAO {

    Optional<WeatherData> getWeather(double latitude, double longitude);

    Optional<WeatherData> getWeather(Mountain mountain);

    Map<Integer, WeatherData> getWeatherForMountains(List<Mountain> mountains);
}