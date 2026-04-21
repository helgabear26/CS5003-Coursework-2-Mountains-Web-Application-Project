package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.beans;


import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao.MountainDAO;
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
import java.util.List;
import java.util.Optional;

@Named("weatherBean")
@ViewScoped
public class WeatherBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private WeatherDAO weatherDAO;

    @Inject
    private MountainDAO mountainDAO;

    private WeatherData weatherData;
    private Date lastUpdate;
    private boolean dataLoaded;
    private String errorMessage;

    private Integer selectedMountainID;
    private Mountain selectedMountain;
    private List<Mountain> allMountains;

    @PostConstruct
    public void init() {
        this.allMountains = mountainDAO.getAllMountains();
    }


    public void onMountainSelected() {
        System.out.println("AJAX is working");
        if (selectedMountainID != null) {
            System.out.println("Selected ID = " + selectedMountainID);
            this.selectedMountain = mountainDAO.getMountainByID(selectedMountainID);
            updateWeather();
        }
    }

    public void updateWeather() {
            try {
                Mountain mountain = selectedMountain != null ?
                        selectedMountain : getSessionMountain();

                if (mountain == null) {
                    setError("Please select a mountain");
                    return;
                }

                Optional<WeatherData> weatherOpt = weatherDAO.getWeather(mountain);

                if (weatherOpt.isPresent()) {
                    this.weatherData = weatherOpt.get();
                    this.lastUpdate = new Date();
                    this.dataLoaded = true;
                    this.errorMessage = null;
                    System.out.println("Weather loaded for: " + mountain.getName());
                } else {
                    setError("Failed to fetch weather for " + mountain.getName());
                }

            } catch (Exception e){
                e.printStackTrace();
                setError("Error: " + e.getMessage());
            }
        }

        private Mountain getSessionMountain() {
            FacesContext context = FacesContext.getCurrentInstance();
            return(Mountain) context.getExternalContext()
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

    public Integer getSelectedMountainID() {
        return selectedMountainID;
    }

    public void setSelectedMountainID(Integer selectedMountainID) {
        this.selectedMountainID = selectedMountainID;
    }

    public Mountain getSelectedMountain() {
        return selectedMountain;
    }

    public List<Mountain> getAllMountains() {
        return allMountains;
    }

}







