package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.client;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.utils.APIKeyConfigManager;
import jakarta.enterprise.context.ApplicationScoped;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@ApplicationScoped
public class WeatherClient {

    // Base URL for OpenWeatherMap API
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    // HTTP client used to send requests
    private final HttpClient client = HttpClient.newHttpClient();

    // Stores API key for authentication
    private final String apiKey;

    // Constructor retrieves API key from configuration manager
    public WeatherClient() {
        this.apiKey = APIKeyConfigManager.getApiKey();
    }

    // Sends a request to the weather API using latitude and longitude
    public String fetch(double lat, double lon) throws Exception {

        // Builds full API request URL with coordinates and API key
        String url = String.format("%s?lat=%.4f&lon=%.4f&appid=%s&units=metric",
                API_URL,
                lat,
                lon,
                apiKey
        );

        // Creates HTTP GET request with timeout
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();

        // Sends request and receives response as a string
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Checks for successful response (HTTP 200)
        if (response.statusCode() != 200) {
            throw new RuntimeException("HTTP error: " + response.statusCode());
        }

        // Returns raw JSON response body
        return response.body();
    }
}