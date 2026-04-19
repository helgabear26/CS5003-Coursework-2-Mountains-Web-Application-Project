package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.parser;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.model.WeatherData;
import org.json.JSONObject;

public class WeatherParser {

    public static WeatherData parse(String jsonString) {

        JSONObject json = new JSONObject(jsonString);

        JSONObject main = json.getJSONObject("main");
        JSONObject wind = json.getJSONObject("wind");

        String condition = "";
        String description = "";
        String imageIcon = "";

        if (json.has("weather") && json.getJSONArray("weather").length() > 0) {
            JSONObject w = json.getJSONArray("weather").getJSONObject(0);
            description = w.getString("description");
            imageIcon = w.getString("icon");
        }

        return new WeatherData(
                main.getDouble("temp"),
                condition,
                wind.getDouble("speed"),
                main.getInt("humidity"),
                description,
                imageIcon
        );
    }

}