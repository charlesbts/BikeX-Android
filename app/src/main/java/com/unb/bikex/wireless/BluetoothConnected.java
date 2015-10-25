package com.unb.bikex.wireless;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Charles on 8/3/2015.
 */
public class BluetoothConnected implements IBluetoothConnected {
    private BluetoothSocket bluetoothSocket;
    private InputStream inputStream;

    @Override
    public void setBluetoothSocket(BluetoothSocket bluetoothSocket){
        this.bluetoothSocket = bluetoothSocket;
    }

    @Override
    public int readByte(){
        int inByte = 0;
        try{
            inputStream = bluetoothSocket.getInputStream();
            inByte = inputStream.read();
        }
        catch (IOException readException){
            Log.d("BluetoothConnected.r", readException.getMessage());
        }
        return inByte;
    }


}
