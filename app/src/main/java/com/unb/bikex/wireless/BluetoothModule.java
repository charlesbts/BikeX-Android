package com.unb.bikex.wireless;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles on 8/5/2015.
 */
@Module(
        library = true
)
public class BluetoothModule {

    @Provides
    @Singleton
    public BluetoothAdapter provideBluetoothAdapter(){
        return BluetoothAdapter.getDefaultAdapter();
    }


    @Provides
    @Singleton
    public IBluetoothConnection provideIBluetoothConnection(BluetoothAdapter bluetoothAdapter){
        return new BluetoothConnection(bluetoothAdapter);
    }

    @Provides
    @Singleton
    public BluetoothSocket provideBluetoothSocket(IBluetoothConnection iBluetoothConnection){
        return iBluetoothConnection.getBluetoothSocket();
    }

    @Provides
    @Singleton
    public IBluetoothConnected provideBluetoothConnected(BluetoothSocket bluetoothSocket){
        return new BluetoothConnected(bluetoothSocket);
    }
}
