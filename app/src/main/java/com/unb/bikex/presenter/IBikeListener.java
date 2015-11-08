package com.unb.bikex.presenter;

/**
 * Created by Charles on 8/17/2015.
 */
public interface IBikeListener {

    void setErrorBluetoothConnection();
    void setSuccessBluetoothConnection(String deviceName);
    void refreshSpeedView(float speed);
    void refreshCadenceView(float cadence);
    void refreshDistanceView(float distance);
    void refreshAverageSpeedView(float averageSpeed);
    void refreshAverageCadenceView(float averageCadence);
    void refreshShift(String shift);
}
