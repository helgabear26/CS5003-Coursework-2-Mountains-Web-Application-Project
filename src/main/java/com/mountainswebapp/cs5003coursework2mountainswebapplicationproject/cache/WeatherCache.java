package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.cache;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.model.WeatherData;

import java.util.concurrent.ConcurrentHashMap;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WeatherCache {

    // Time to live for cached weather data (2 minutes)
    private static final long TTL_MS = 2 * 60 * 1000;

    // Stores cached weather data by mountain ID
    private final ConcurrentHashMap<Integer, CacheItem> cache = new ConcurrentHashMap<>();

    // Returns cached weather data if it exists and has not expired
    public WeatherData get(Integer id) {
        CacheItem item = cache.get(id);

        // Return null if no cache entry exists
        if (item == null)
            return null;

        // Remove cache entry if it has expired
        if (System.currentTimeMillis() - item.timestamp > TTL_MS) {
            cache.remove(id);
            return null;
        }

        // Return valid cached weather data
        return item.data;
    }

    // Stores weather data in cache
    public void put(Integer id, WeatherData data) {
        cache.put(id, new CacheItem(data));
    }

    // Inner class used to store weather data with timestamp
    private static class CacheItem {
        WeatherData data;
        long timestamp;

        CacheItem(WeatherData data) {
            this.data = data;
            this.timestamp = System.currentTimeMillis();
        }
    }
}