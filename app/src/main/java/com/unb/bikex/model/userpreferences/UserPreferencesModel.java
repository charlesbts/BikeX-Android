package com.unb.bikex.model.userpreferences;

import com.unb.bikex.sharedpreferences.UserSharedPreferences;

import java.util.IllegalFormatCodePointException;

/**
 * Created by Charles on 9/16/2015.
 */
public class UserPreferencesModel implements IUserPreferencesModel{
    private UserSharedPreferences userSharedPreferences;
    private final int MIN_WHEEL_SIZE = 18;
    private final int MAX_WHEEL_SIZE = 33;
    private String bluetoothMacAddress;
    private int wheelSize;

    public UserPreferencesModel(UserSharedPreferences userSharedPreferences){
        this.userSharedPreferences = userSharedPreferences;
    }

    @Override
    public void setBluetoothMacAddress(String bluetoothMacAddress){
        this.bluetoothMacAddress = bluetoothMacAddress;
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
    public void save(){
        userSharedPreferences.save(wheelSize, bluetoothMacAddress);
    }
}
