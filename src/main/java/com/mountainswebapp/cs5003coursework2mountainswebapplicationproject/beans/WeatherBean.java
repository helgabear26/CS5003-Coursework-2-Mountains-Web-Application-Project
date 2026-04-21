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

    // DAO used to retrieve weather data
    @Inject
    private WeatherDAO weatherDAO;

    // DAO used to retrieve mountain data
    @Inject
    private MountainDAO mountainDAO;

    // Stores the current weather data
    private WeatherData weatherData;

    // Stores the time of the last weather update
    private Date lastUpdate;

    // Indicates whether weather data has been loaded successfully
    private boolean dataLoaded;

    // Stores error messages if weather cannot be loaded
    private String errorMessage;

    // Stores selected mountain ID from dropdown
    private Integer selectedMountainID;

    // Stores selected mountain object
    private Mountain selectedMountain;

    // List of all mountains for the dropdown menu
    private List<Mountain> allMountains;

    // Loads all mountains when the page is opened
    @PostConstruct
    public void init() {
        this.allMountains = mountainDAO.getAllMountains();
    }

    // Runs when a mountain is selected from the dropdown
    public void onMountainSelected() {
        System.out.println("AJAX is working");

        if (selectedMountainID != null) {
            System.out.println("Selected ID = " + selectedMountainID);

            // Retrieve selected mountain from database
            this.selectedMountain = mountainDAO.getMountainByID(selectedMountainID);

            // Update weather data for selected mountain
            updateWeather();
        }
    }

    // Retrieves latest weather data for the selected mountain
    public void updateWeather() {
        System.out.println("HIT UPDATE WEATHER");

        try {

            // Ensure a mountain has been selected
            if (selectedMountainID == null && selectedMountain == null) {
                setError("Please select a mountain");
                return;
            }

            Mountain mountain = selectedMountain;

            // If mountain object is not set, retrieve it using selected ID
            if (mountain == null) {
                mountain = mountainDAO.getMountainByID(selectedMountainID);
            }

            // Handle case where mountain is not found
            if (mountain == null) {
                setError("Mountain not found");
                return;
            }

            // Retrieve weather data for selected mountain
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
            e.printStackTrace();
            setError(e.getMessage());
        }
    }

    // Retrieves selected mountain from session if needed
    private Mountain getSessionMountain() {
        FacesContext context = FacesContext.getCurrentInstance();
        return (Mountain) context.getExternalContext()
                .getSessionMap()
                .get("selectedMountain");
    }

    // Sets an error message and marks data as not loaded
    private void setError(String message) {
        this.errorMessage = message;
        this.dataLoaded = false;
    }

    // Returns formatted temperature string
    public String getFormattedTemperature() {
        return weatherData != null
                ? String.format("%.1f°C", weatherData.getTemperature())
                : "N/A";
    }

    // Returns formatted wind speed string
    public String getFormattedWindSpeed() {
        return weatherData != null
                ? String.format("%.1f km/h", weatherData.getWindSpeed())
                : "N/A";
    }

    // Returns formatted last update time
    public String getFormattedLastUpdate() {
        if (lastUpdate == null) return "Never";
        return new SimpleDateFormat("HH:mm:ss").format(lastUpdate);
    }

    // Returns raw weather data object
    public WeatherData getWeatherData() {
        return weatherData;
    }

    // Returns whether data has been loaded
    public boolean isDataLoaded() {
        return this.dataLoaded;
    }

    // Returns error message
    public String getErrorMessage() {
        return errorMessage;
    }

    // Returns last update time
    public Date getLastUpdate() {
        return lastUpdate;
    }

    // Returns weather condition
    public String getCondition() {
        return weatherData != null ? weatherData.getCondition() : null;
    }

    // Returns weather description
    public String getDescription() {
        return weatherData != null ? weatherData.getDescription() : null;
    }

    // Returns weather icon code
    public String getImageIcon() {
        return weatherData != null ? weatherData.getImageIcon() : null;
    }

    // Returns humidity value
    public Integer getHumidity() {
        return weatherData != null ? weatherData.getHumidity() : null;
    }

    // Returns selected mountain ID
    public Integer getSelectedMountainID() {
        return selectedMountainID;
    }

    // Sets selected mountain ID
    public void setSelectedMountainID(Integer selectedMountainID) {
        this.selectedMountainID = selectedMountainID;
    }

    // Returns selected mountain
    public Mountain getSelectedMountain() {
        return selectedMountain;
    }

    // Returns all mountains for dropdown
    public List<Mountain> getAllMountains() {
        return allMountains;
    }
}