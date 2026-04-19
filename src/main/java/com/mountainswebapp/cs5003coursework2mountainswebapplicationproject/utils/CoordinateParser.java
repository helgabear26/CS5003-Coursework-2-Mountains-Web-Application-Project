package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.utils;

import java.util.Optional;

public class CoordinateParser {

    public static Optional<double[]> parse(String coordinates) {
        if (coordinates == null || coordinates.isBlank()) {
            return Optional.empty();
        }

        try {
            String[] parts = coordinates.split(",");
            if (parts.length != 2) return Optional.empty();

            double lat = Double.parseDouble(parts[0].trim());
            double lon = Double.parseDouble(parts[1].trim());

            if (lat < -90 || lat > 90 || lon < -180 | lon > 180) {
                return Optional.empty();
            }

            return Optional.of(new double[] {
                    lat, lon
            });
        } catch (Exception e) {
            return Optional.empty();
        }
    }


}
