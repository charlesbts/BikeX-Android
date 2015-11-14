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
    void refreshAverageSpeed(String averageSpeed);
    void refreshAverageCadence(String averageCadence);
    void refreshShiftRedColor(String shift);
    void refreshShiftGreenColor(String shift);
    void startTrack();
    void startUserPreferencesActivity();
}
