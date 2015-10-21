package com.unb.bikex.model;


/**
 * Created by Charles on 10/20/2015.
 */
public class DataLocation {
    private double latitude;
    private double longitude;

    public DataLocation(double latitude, double longitude){
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
