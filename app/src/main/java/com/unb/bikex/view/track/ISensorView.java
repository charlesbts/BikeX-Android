package com.unb.bikex.view.track;

/**
 * Created by Charles on 8/16/2015.
 */
public interface ISensorView {
    void requestBluetoothEnable(String bluetoothEnable);
    void showBluetoothConnectionProgressDialog();
    void hideBluetoothConnectionProgressDialog();
    void showErrorBluetoothConnection();
    void showSuccessBluetoothConnection(String deviceName);
    void finishWithShowErrorBluetoothEnable();
    void refreshSpeed(String speed);
    void refreshCadence(String cadence);
    void refreshDistance(String distance);
    void startTrack();
    void hideStartTrackButton();
    void startUserPreferencesActivity();
}