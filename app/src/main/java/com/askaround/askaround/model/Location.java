package com.askaround.askaround.model;

import java.util.Date;

/**
 * Created by sibi on 10/2/16.
 */
public class Location {

    private double lat;
    private double lon;
    private String username;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String key) {
        this.username = key;
    }
}
