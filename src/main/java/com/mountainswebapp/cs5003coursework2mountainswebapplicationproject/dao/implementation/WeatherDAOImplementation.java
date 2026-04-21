package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.implementation;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.cache.WeatherCache;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.client.WeatherClient;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.WeatherDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Mountain;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.model.WeatherData;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.parser.WeatherParser;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.utils.APIKeyConfigManager;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.utils.CoordinateParser;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


import java.util.*;
import java.util.logging.Logger;

@ApplicationScoped
public class WeatherDAOImplementation implements WeatherDAO {

    private static final Logger LOGGER = Logger.getLogger(WeatherDAOImplementation.class.getName());

    @Inject
    private  WeatherClient client;

    @Inject
    private WeatherCache cache;

    @Override
    public Optional<WeatherData> getWeather(double lat, double lon) {
        try {
            String json = client.fetch(lat, lon);
            return Optional.ofNullable(WeatherParser.parse(json));

        } catch (Exception e) {
            LOGGER.warning("Weather fetch failed: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<WeatherData> getWeather(Mountain mountain) {
        return CoordinateParser.parse(mountain.getCoordinates())
                .flatMap(coords -> getWeather(coords[0], coords[1]));
    }

    @Override
    public Map<Integer, WeatherData> getWeatherForMountains(List<Mountain> mountains) {

        Map<Integer, WeatherData> result = new HashMap<>();

        for (Mountain mountain : mountains) {
            Integer id = mountain.getId();

            try {
                WeatherData cached = cache.get(id);
                if (cached != null) {
                    result.put(id, cached);
                    continue;
                }

                Optional<double[]> coordsOpt = CoordinateParser.parse(mountain.getCoordinates());

                if (coordsOpt.isEmpty()) {
                    LOGGER.warning("Invalid coordinates for " + mountain.getName());
                    continue;
                }

                double[] coords = coordsOpt.get();

                Optional<WeatherData> weather = getWeather(coords[0], coords[1]);

                weather.ifPresent(data -> {
                    cache.put(id, data);
                    result.put(id, data);
                });

            } catch (Exception e) {
                LOGGER.severe("Error processing " + mountain.getName() + ": " + e.getMessage());
            }
        }

        return result;
    }
}



