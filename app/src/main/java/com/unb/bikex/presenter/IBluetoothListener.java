package com.unb.bikex.presenter;

/**
 * Created by Charles on 8/17/2015.
 */
public interface IBluetoothListener {

    void setErrorBluetoothConnection();
    void setSuccessBluetoothConnection(String deviceName);
    void refreshSpeedView(float speed);
    void refreshCadenceView(float speed);
    void refreshDistanceView(float distance);
}
