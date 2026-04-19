package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;


import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.WeatherDAO;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Mountain;
import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.model.WeatherData;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Named("weatherBean")
@ViewScoped
public class WeatherBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private WeatherDAO weatherDAO;

    private WeatherData weatherData;
    private Date lastUpdate;
    private boolean dataLoaded;
    private String errorMessage;

    @PostConstruct
    public void init() {
        updateWeather();
    }

    public void updateWeather() {
        try {
            Mountain mountain = getSelectedMountain();

            if (mountain == null) {
                setError("Mountain not selected");
                return;
            }

            Optional<WeatherData> weatherOpt = weatherDAO.getWeather(mountain);

            if (weatherOpt.isPresent()) {
                this.weatherData = weatherOpt.get();
                this.lastUpdate = new Date();
                this.dataLoaded = true;
                this.errorMessage = null;
            } else {
                setError("Failed to fetch weather");
            }
        } catch (Exception e) {
            setError("Error: " + e.getMessage());
        }
    }

        private Mountain getSelectedMountain() {
            FacesContext context = FacesContext.getCurrentInstance();

            return (Mountain) context.getExternalContext()
                    .getSessionMap()
                    .get("selectedMountain");
        }

        private void setError(String message) {
            this.errorMessage = message;
            this.dataLoaded = false;
        }

        public String getFormattedTemperature() {
            return weatherData != null
                ? String.format("%.1f°C", weatherData.getTemperature())
                : "N/A";
        }

        public String getFormattedWindSpeed() {
            return weatherData != null
                    ? String.format("%.1f km/h", weatherData.getWindSpeed())
                    : "N/A";
        }

    public String getFormattedLastUpdate() {
        if (lastUpdate == null) return "Never";
        return new SimpleDateFormat("HH:mm:ss").format(lastUpdate);
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public boolean isDataLoaded() {
        return this.dataLoaded;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public String getCondition() {
        return weatherData != null ? weatherData.getCondition() : null;
    }

    public String getDescription() {
        return weatherData != null ? weatherData.getDescription() : null;
    }

    public String getImageIcon() {
        return weatherData != null ? weatherData.getImageIcon() : null;
    }

    public Integer getHumidity() {
        return weatherData != null ? weatherData.getHumidity() : null;
    }


}





