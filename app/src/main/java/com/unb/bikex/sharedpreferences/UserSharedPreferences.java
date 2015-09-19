package com.unb.bikex.sharedpreferences;

import android.content.SharedPreferences;

import com.unb.bikex.app.BikeXApp;
import com.unb.bikex.view.IUserPreferencesView;

/**
 * Created by Charles on 9/13/2015.
 */
public class UserSharedPreferences {

    SharedPreferences sharedPreferences;
    private final String SHARED_PREFERENCES_KEY = "userPreferences";
    private final String WHEEL_SIZE_KEY = "wheelSize";
    private final String BLUETOOTH_MAC_ADDRESS = "bluetoothMacAddress";

    public UserSharedPreferences(BikeXApp context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, BikeXApp.MODE_PRIVATE);
    }

    public void save(int wheelSize, String bluetoothMacAddress){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putInt(WHEEL_SIZE_KEY, wheelSize);
        editor.putString(BLUETOOTH_MAC_ADDRESS, bluetoothMacAddress);
        editor.commit();
    }

    public String retrieveBluetoothMacAddress(){
        String bluetoothMacAddress = null;
        if(sharedPreferences.contains(BLUETOOTH_MAC_ADDRESS)){
            bluetoothMacAddress = sharedPreferences.getString(BLUETOOTH_MAC_ADDRESS, "");
        }
        return bluetoothMacAddress;
    }

}
