package com.unb.bikex.view.track;


/**
 * Created by Charles on 10/20/2015.
 */
public interface IMapTrackView {
    void moveCamera(double latitude, double longitude);
    void drawRedMarker(double latitude, double longitude);
    void changeColorInitialMarker();
    void removeInitialMarker();
    void notifyFirstMarkerAchieve();
    void notifyLastMarkerAchieve();
    void showDebugMessage(String message);
}
