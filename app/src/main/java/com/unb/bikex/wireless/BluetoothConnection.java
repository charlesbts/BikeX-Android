package com.unb.bikex.wireless;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import com.unb.bikex.threadutils.GenericThread;
import com.unb.bikex.threadutils.ICallbackThread;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by Charles on 8/2/2015.
 */
public class BluetoothConnection extends GenericThread implements IBluetoothConnection{
    private final String uuid = "00001101-0000-1000-8000-00805f9b34fb";
    private String address;
    private boolean flagIsConnected = false;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket bluetoothSocket;

    public BluetoothConnection(BluetoothAdapter bluetoothAdapter){
        this.bluetoothAdapter = bluetoothAdapter;
    }

    @Override
    public void setListener(ICallbackThread iListener){
        super.setListener(iListener);
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
    public void connectToDevice(String address){
        super.threadId = 15;
        this.address = address;
        new Thread(this).start();
    }

    @Override
    public void doBackground(){
        try{
            setupBluetoothSocket(address);
            bluetoothSocket.connect();
            flagIsConnected = true;
        }
        catch(IOException connectException){
            Log.d("connectException", connectException.getMessage());
        }
    }

    @Override
    public void disconnectToDevice() throws IOException{
        if(flagIsConnected) {
            bluetoothSocket.close();
            flagIsConnected = false;
        }
    }

    @Override
    public boolean isConnected(){
        return flagIsConnected;
    }

    public void setupBluetoothSocket(String address) throws IOException{
        if(BluetoothAdapter.checkBluetoothAddress(address)){
            bluetoothDevice = bluetoothAdapter.getRemoteDevice(address);
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString(uuid));
        }
        else{
            throw new IOException("Address invalid!");
        }
    }
    @Override
    public BluetoothSocket getBluetoothSocket(){
        return bluetoothSocket;
    }

    @Override
    public String getDeviceName(){
        return bluetoothDevice.getName();
    }

}
