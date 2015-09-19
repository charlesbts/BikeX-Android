package com.unb.bikex.model.userpreferences;

import java.util.List;

/**
 * Created by Charles on 9/13/2015.
 */
public interface IUserPreferencesModel {
    void setBluetoothMacAddress(String bluetoothMacAddress);
    void setWheelSize(int wheelSize) throws IllegalArgumentException;
    void save();
    List<String> getBluetoothDeviceList();
}
