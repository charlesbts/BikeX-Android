package com.unb.bikex.view.track;


/**
 * Created by Charles on 10/20/2015.
 */
public interface IMapTrackView {
    void moveCamera(double latitude, double longitude, float zoom);
    void drawRedMarker(double latitude, double longitude);
    void drawGreenMarker(double latitude, double longitude);
}
