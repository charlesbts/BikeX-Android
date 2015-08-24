package com.unb.bikex.wireless;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

/**
 * Created by Charles on 7/28/2015.
 */
public class BluetoothSetup implements IBluetoothSetup{
    BluetoothAdapter bluetoothAdapter;

    @Inject
    public BluetoothSetup(BluetoothAdapter bluetoothAdapter){
        this.bluetoothAdapter = bluetoothAdapter;
    }

    @Override
    public String enable(){
        if(!bluetoothAdapter.isEnabled()) {
            return BluetoothAdapter.ACTION_REQUEST_ENABLE;
        }
        else
            return null;
    }

    @Override
    public List<String> getPairedDeviceList(){
        List<String> pairedDeviceList = new ArrayList<>();
        Set<BluetoothDevice> bluetoothDeviceSet = bluetoothAdapter.getBondedDevices();
        if (bluetoothDeviceSet.size() > 0) {
            for (BluetoothDevice bluetoothDevice : bluetoothDeviceSet){
                pairedDeviceList.add(bluetoothDevice.getName() + "\n" + bluetoothDevice.getAddress());

            }
        }
        return pairedDeviceList;
    }


}
