package com.unb.bikex.wireless;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.unb.bikex.threadutils.GenericThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Charles on 8/3/2015.
 */
public class BluetoothConnected extends GenericThread implements IBluetoothConnected {
    private BluetoothSocket bluetoothSocket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private byte inByte;

    public BluetoothConnected(BluetoothSocket bluetoothSocket){
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
    public void readByte(){
        new Thread(this).start();
    }

    @Override
    public void doBackground(){
        try{
            inputStream = bluetoothSocket.getInputStream();
            inByte = (byte) inputStream.read();
        }
        catch (IOException readException){
            Log.d("BluetoothConnected.r", readException.getMessage());
        }
    }

}
