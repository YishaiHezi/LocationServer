package com.example.demo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


/**
 * A user that has a name and location.
 */
@DynamoDBTable(tableName = "User")
public class User {

    private String id;
    private String name;
    private Double lat;
    private Double lon;


    @DynamoDBHashKey(attributeName = "id")
    public String getId(){ return id; }

    public void setId(String id) {this.id = id;}


    @DynamoDBAttribute(attributeName = "name")
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}


    @DynamoDBAttribute(attributeName = "lat")
    public Double getLat() {return lat;}

    public void setLat(Double lat) {this.lat = lat;}


    @DynamoDBAttribute(attributeName = "lon")
    public Double getLon() {return lon;}


    public void setLon(Double lon) {this.lon = lon;}



}
