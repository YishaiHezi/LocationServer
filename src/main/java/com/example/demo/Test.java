package com.example.demo;

/**
 * This class is only for testing.
 */
public class Test {

    private String id;
    private String name;
    private Double lat;
    private Double lon;

    public Test(String id, String name, Double lat, Double lon){
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}



