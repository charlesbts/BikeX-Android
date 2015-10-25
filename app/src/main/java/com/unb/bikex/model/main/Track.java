package com.unb.bikex.model.main;

/**
 * Created by Charles on 10/24/2015.
 */
public class Track {
    private long cod;
    private String name;

    public Track(long cod, String name){
        this.cod = cod;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public long getCod(){
        return cod;
    }

}
