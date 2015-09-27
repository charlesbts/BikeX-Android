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
    private OutputStream outputStream;
    private InputStream inputStream;

    @Override
    public void setBluetoothSocket(BluetoothSocket bluetoothSocket){
        this.bluetoothSocket = bluetoothSocket;
    }

    @Override
    public void writeByte(byte outByte){
        try{
            outputStream = bluetoothSocket.getOutputStream();
            outputStream.write(outByte);
        }
        catch (IOException writeException){
            Log.d("BluetoothConnected.w", writeException.getMessage());
        }
    }

    @Override
    public int readByte(){
        int inByte = 0;
        try{
            inputStream = bluetoothSocket.getInputStream();
            inByte = inputStream.read();
            Log.d("NewByte", String.valueOf(inByte));
        }
        catch (IOException readException){
            Log.d("BluetoothConnected.r", readException.getMessage());
        }
        return inByte;
    }


}
