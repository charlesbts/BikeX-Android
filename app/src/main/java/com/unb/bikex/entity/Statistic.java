package com.unb.bikex.entity;

/**
 * Created by Charles on 11/16/2015.
 */
public class Statistic {
    private long cod;
    private String timeStamp;
    private float averageCadence;
    private float averageSpeed;
    private int elapsedTime;
    private float distance;

    public Statistic(long cod, String timeStamp, float averageCadence,
                     float averageSpeed, int elapsedTime, float distance){
        this.cod = cod;
        this.timeStamp = timeStamp;
        this.averageCadence = averageCadence;
        this.averageSpeed = averageSpeed;
        this.elapsedTime = elapsedTime;
        this.distance = distance;
    }

    public long getCod(){
        return cod;
    }

    public String getTimeStamp(){
        return timeStamp;
    }

    public float getAverageCadence(){
        return averageCadence;
    }

    public float getAverageSpeed(){
        return averageSpeed;
    }

    public int getElapsedTime(){
        return elapsedTime;
    }

    public float getDistance(){
        return distance;
    }
}
