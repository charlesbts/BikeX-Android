package com.unb.bikex.model.newtrack;

/**
 * Created by Charles on 10/30/2015.
 */
public interface INewTrackModel {
    void addDataLocation(double latitude, double longitude);
    void removeDataLocation() throws IndexOutOfBoundsException;
}
