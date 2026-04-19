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

    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    private final HttpClient client = HttpClient.newHttpClient();
    private final String apiKey;

    public WeatherClient() {
        this.apiKey = APIKeyConfigManager.getApiKey();
    }

    public String fetch(double lat, double lon) throws Exception {
        String url = String.format("%s?lat=%.4f&lon=%.4f&appid=%s&units=metric",
                API_URL,
                lat,
                lon,
                apiKey
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("HTTP error: " + response.statusCode());
        }

        return response.body();
    }
}
