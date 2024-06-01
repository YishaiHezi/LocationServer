package com.example.demo.model;

import org.springframework.lang.NonNull;


/**
 * An object that holds information about a user location that was recorded in specific time.
 */
public class LastSeen {


    /**
     * The name of the user that was last seen.
     */
    @NonNull
    String name;


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


    /**
     * The time when this lat & lon where recorded
     */
    long timestamp;


    public LastSeen(@NonNull String name, @NonNull Double lat, @NonNull Double lon, long timestamp) {
        this.lat = lat;
        this.lon = lon;
        this.timestamp = timestamp;
        this.name = name;

    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public Double getLat() {
        return lat;
    }

    @NonNull
    public Double getLon() {
        return lon;
    }

    public long getTimestamp() {
        return timestamp;
    }


}
