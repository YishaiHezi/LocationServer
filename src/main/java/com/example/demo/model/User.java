package com.example.demo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


/**
 * A user that has a name and location.
 */
@DynamoDBTable(tableName = "User")
public class User {


    /**
     * Unique id for each user.
     */
    private String id;

    /**
     * The mail of the user.
     */
    private String mail;


    /**
     * The password of the user.
     */
    private String password;

    /**
     * The name of the user.
     */
    private String name;


    /**
     * The time when the location (lat & lon) were recorded. (UTC)
     */
    private long lastTimeChecked;


    /**
     * the latitude value of the location of the user.
     */
    private Double lat;


    /**
     * the longitude value of the location of the user.
     */
    private Double lon;


    /**
     * The fcm token of the user. used for messaging with firebase.
     */
    private String fcmToken;


    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "mail")
    public String getMail() {
        return mail;
    }


    public void setMail(String mail) {
        this.mail = mail;
    }


    @DynamoDBAttribute(attributeName = "password")
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @DynamoDBAttribute(attributeName = "lastTimeChecked")
    public long getLastTimeChecked() {
        return lastTimeChecked;
    }

    public void setLastTimeChecked(long lastTimeChecked) {
        this.lastTimeChecked = lastTimeChecked;
    }

    @DynamoDBAttribute(attributeName = "lat")
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }


    @DynamoDBAttribute(attributeName = "lon")
    public Double getLon() {
        return lon;
    }


    public void setLon(Double lon) {
        this.lon = lon;
    }


    @DynamoDBAttribute(attributeName = "fcmToken")
    public String getFcmToken() {
        return fcmToken;
    }


    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

}
