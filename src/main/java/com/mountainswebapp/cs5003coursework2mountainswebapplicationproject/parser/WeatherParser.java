package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.parser;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.model.WeatherData;
import org.json.JSONObject;

public class WeatherParser {

    public static WeatherData parse(String jsonString) {

        JSONObject json = new JSONObject(jsonString);

        JSONObject main = json.getJSONObject("main");

        double temperature = main.getDouble("temp");
        int humidity = main.getInt("humidity");

        double windSpeed = 0.0;

        if (json.has("wind") && !json.isNull("wind")) {
            windSpeed = json.getJSONObject("wind").optDouble("speed", 0.0);
        }

        String condition = "";
        String description = "";
        String imageIcon = "";

        if (json.has("weather") && json.getJSONArray("weather").length() > 0) {
            JSONObject w = json.getJSONArray("weather").getJSONObject(0);

            condition = w.optString("main", "");
            description = w.optString("description", "");
            imageIcon = w.optString("icon", "");
        }

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