package com.unb.bikex.model.userpreferences;

import com.unb.bikex.sharedpreferences.UserSharedPreferences;
import com.unb.bikex.wireless.IBluetoothSetup;

import java.util.List;

/**
 * Created by Charles on 9/16/2015.
 */
public class UserPreferencesModel implements IUserPreferencesModel{
    private UserSharedPreferences userSharedPreferences;
    private IBluetoothSetup iBluetoothSetup;
    private final int MIN_WHEEL_SIZE = 18;
    private final int MAX_WHEEL_SIZE = 33;
    private String bluetoothMacAddress;
    private int wheelSize;
    private float desiredCadence;

    public UserPreferencesModel(UserSharedPreferences userSharedPreferences, IBluetoothSetup iBluetoothSetup){
        this.userSharedPreferences = userSharedPreferences;
        this.iBluetoothSetup = iBluetoothSetup;
    }

    @Override
    public void setBluetoothMacAddress(String bluetoothMacAddress){
        this.bluetoothMacAddress = bluetoothMacAddress;
    }

    @Override
    public void setDesiredCadence(float desiredCadence){
        this.desiredCadence = desiredCadence;
    }

    @Override
    public void setWheelSize(int wheelSize) throws IllegalArgumentException{
        if(wheelSize >= MIN_WHEEL_SIZE && wheelSize <= MAX_WHEEL_SIZE){
            this.wheelSize = wheelSize;
        }
        else{
            throw new IllegalArgumentException("Wheel size should be between " + MIN_WHEEL_SIZE + " and " + MAX_WHEEL_SIZE);
        }
    }

    @Override
    public int getWheelSize(){
        return userSharedPreferences.retrieveWheelSize();
    }

    @Override
    public float getDesiredCadence(){
        return userSharedPreferences.retrieveDesiredCadence();
    }

    @Override
    public String getBluetoothMacAddress(){
        return userSharedPreferences.retrieveBluetoothMacAddress();
    }

    @Override
    public void save(){
        userSharedPreferences.save(wheelSize, desiredCadence, bluetoothMacAddress);
    }

    @Override
    public List<String> getBluetoothDeviceList() throws IllegalStateException{
        String enable = iBluetoothSetup.enable();
        if(enable == null) {
            return iBluetoothSetup.getPairedDeviceList();
        }
        else{
            throw new IllegalStateException(enable);
        }
    }
}
