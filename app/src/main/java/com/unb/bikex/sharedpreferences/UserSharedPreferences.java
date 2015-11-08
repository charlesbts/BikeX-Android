package com.unb.bikex.sharedpreferences;

import android.content.SharedPreferences;

import com.unb.bikex.application.BikeXApp;

/**
 * Created by Charles on 9/13/2015.
 */
public class UserSharedPreferences {

    SharedPreferences sharedPreferences;
    private final String SHARED_PREFERENCES_KEY = "userPreferences";
    private final String WHEEL_SIZE_KEY = "wheelSize";
    private final String DESIRED_CADENCE_KEY = "desiredCadence";
    private final String BLUETOOTH_MAC_ADDRESS_KEY = "bluetoothMacAddress";

    public UserSharedPreferences(BikeXApp context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, BikeXApp.MODE_PRIVATE);
    }

    public void save(int wheelSize, float desiredCadence, String bluetoothMacAddress){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt(WHEEL_SIZE_KEY, wheelSize);
        editor.putFloat(DESIRED_CADENCE_KEY, desiredCadence);
        editor.putString(BLUETOOTH_MAC_ADDRESS_KEY, bluetoothMacAddress);
        editor.commit();
    }

    public String retrieveBluetoothMacAddress(){
        String bluetoothMacAddress = null;
        if(sharedPreferences.contains(BLUETOOTH_MAC_ADDRESS_KEY)){
            bluetoothMacAddress = sharedPreferences.getString(BLUETOOTH_MAC_ADDRESS_KEY, "");
        }
        return bluetoothMacAddress;
    }

    public float retrieveDesiredCadence(){
        float desiredCadence = 0;
        if(sharedPreferences.contains(DESIRED_CADENCE_KEY)){
            desiredCadence = sharedPreferences.getFloat(DESIRED_CADENCE_KEY, 0);
        }
        return desiredCadence;
    }

    public int retrieveWheelSize(){
        int wheelSize = 0;
        if(sharedPreferences.contains(WHEEL_SIZE_KEY)){
            wheelSize = sharedPreferences.getInt(WHEEL_SIZE_KEY, 0);
        }
        return wheelSize;
    }

}
