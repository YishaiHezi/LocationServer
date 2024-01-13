package com.example.demo.model;

import org.springframework.lang.NonNull;


/**
 * Represents a location on earth.
 */
public class Location {


    /**
     * The latitude.
     */
    @NonNull
    Double lat;


    /**
     * The longitude.
     */
    @NonNull
    Double lon;


    public Location(@NonNull Double lat, @NonNull Double lon) {
        this.lat = lat;
        this.lon = lon;
    }


    @NonNull
    public Double getLat() {
        return lat;
    }


    @NonNull
    public Double getLon() {
        return lon;
    }


}
