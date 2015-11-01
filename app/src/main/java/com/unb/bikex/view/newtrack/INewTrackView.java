package com.unb.bikex.view.newtrack;

/**
 * Created by Charles on 10/20/2015.
 */
public interface INewTrackView{

    void drawMarker(double latitude, double longitude);
    void removeMarker();
    void showRemoveMarkerError(String message);
    void showSaveSuccess();
}
