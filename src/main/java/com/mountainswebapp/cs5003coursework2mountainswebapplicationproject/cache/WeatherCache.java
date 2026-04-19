package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.cache;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.model.WeatherData;

import java.util.concurrent.ConcurrentHashMap;

public class WeatherCache {

    private static final long  TTL_MS = 2 * 60 * 1000;

    private final ConcurrentHashMap<Integer, CacheItem> cache = new ConcurrentHashMap<>();

    public WeatherData get(Integer id) {
        CacheItem item = cache.get(id);

        if (item == null)
            return null;

        if (System.currentTimeMillis() - item.timestamp > TTL_MS) {
            cache.remove(id);
            return null;
        }

        return item.data;
    }

    public void put(Integer id, WeatherData data) {
        cache.put(id, new CacheItem(data));
    }

    private static class CacheItem {
        WeatherData data;
        long timestamp;

        CacheItem(WeatherData data) {
            this.data = data;
            this.timestamp = System.currentTimeMillis();
        }
    }
}
