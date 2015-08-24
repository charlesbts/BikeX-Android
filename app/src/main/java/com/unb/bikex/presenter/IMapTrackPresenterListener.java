package com.unb.bikex.presenter;

/**
 * Created by Charles on 8/17/2015.
 */
public interface IMapTrackPresenterListener {

    void setErrorBluetoothConnection();
    void setSuccessBluetoothConnection(String deviceName);
}
