package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.model;

/*
 * WeatherData model class
 * Stores weather information retrieved from the external API
 */
public class WeatherData {

    // Stores temperature value (in Celsius)
    private Double temperature;

    // Stores general weather condition (e.g. Rain, Clear)
    private String condition;

    // Stores wind speed value
    private Double windSpeed;

    // Stores humidity percentage
    private Integer humidity;

    // Stores detailed weather description
    private String description;

    // Stores icon code for weather image
    private String imageIcon;

    // Constructor used to initialise all weather fields
    public WeatherData(double temperature, String condition, double windSpeed, int humidity, String description, String imageIcon) {
        this.temperature = temperature;
        this.condition = condition;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.description = description;
        this.imageIcon = imageIcon;
    }

    // Returns temperature value
    public Double getTemperature() {
        return temperature;
    }

    // Returns main weather condition
    public String getCondition() {
        return this.condition;
    }

    // Returns wind speed
    public Double getWindSpeed() {
        return windSpeed;
    }

    // Returns humidity value
    public Integer getHumidity() {
        return humidity;
    }

    // Sets humidity value
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    // Returns detailed description of weather
    public String getDescription() {
        return description;
    }

    // Returns weather icon code
    public String getImageIcon() {
        return imageIcon;
    }
}