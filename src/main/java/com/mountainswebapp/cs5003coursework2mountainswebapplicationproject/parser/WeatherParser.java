package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.parser;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.model.WeatherData;
import org.json.JSONObject;

public class WeatherParser {

    // Parses JSON response from weather API into a WeatherData object
    public static WeatherData parse(String jsonString) {

        // Convert raw JSON string into JSONObject
        JSONObject json = new JSONObject(jsonString);

        // Extract main weather data (temperature and humidity)
        JSONObject main = json.getJSONObject("main");

        double temperature = main.getDouble("temp");
        int humidity = main.getInt("humidity");

        // Default wind speed value
        double windSpeed = 0.0;

        // Extract wind speed if available
        if (json.has("wind") && !json.isNull("wind")) {
            windSpeed = json.getJSONObject("wind").optDouble("speed", 0.0);
        }

        // Default weather condition values
        String condition = "";
        String description = "";
        String imageIcon = "";

        // Extract weather condition details if available
        if (json.has("weather") && json.getJSONArray("weather").length() > 0) {
            JSONObject w = json.getJSONArray("weather").getJSONObject(0);

            condition = w.optString("main", "");
            description = w.optString("description", "");
            imageIcon = w.optString("icon", "");
        }

        // Return populated WeatherData object
        return new WeatherData(
                temperature,
                condition,
                windSpeed,
                humidity,
                description,
                imageIcon
        );
    }
}