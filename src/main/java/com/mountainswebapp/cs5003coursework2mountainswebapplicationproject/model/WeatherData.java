package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.model;

public class WeatherData {

    private Double temperature;
    private String condition;
    private Double windSpeed;
    private Integer humidity;
    private String description;
    private String imageIcon;

    public WeatherData(double temperature, String condition, double windSpeed, int humidity, String description, String imageIcon) {
        this.temperature = temperature;
        this.condition = condition;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.description = description;
        this.imageIcon = imageIcon;
    }

    public Double getTemperature() {

        return temperature;
    }

    public String getCondition() {

        return this.condition;
    }

    public Double getWindSpeed() {

        return windSpeed;
    }

    public Integer getHumidity() {

        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getDescription() {

        return description;
    }

    public String getImageIcon() {

        return imageIcon;
    }
}
