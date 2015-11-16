package com.unb.bikex.entity;


/**
 * Created by Charles on 10/20/2015.
 */
public class Location {
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longitude;
    }
}
