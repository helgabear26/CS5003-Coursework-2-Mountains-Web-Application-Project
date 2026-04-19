package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.utils;

import java.io.InputStream;
import java.util.Properties;

public class APIKeyConfigManager {

    public static String getApiKey() {

        String environmentKey = System.getenv("OPENWEATHER_API_KEY");

        if (environmentKey != null && !environmentKey.isBlank()) {
            return environmentKey;
        }

        try (InputStream input = APIKeyConfigManager.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input != null) {
                Properties props = new Properties();
                props.load(input);

                String key = props.getProperty("openweather.api.key");

                if (key != null && !key.isBlank() && !key.equals("MY_API_KEY_HERE")) {
                    return key;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("API key is not configured! ");
    }
}

