package com.unb.bikex.wireless;

import android.bluetooth.BluetoothSocket;

import com.unb.bikex.threadutils.ICallbackThread;

/**
 * Created by Charles on 8/3/2015.
 */
public interface IBluetoothConnected {


    public void setBluetoothSocket(BluetoothSocket bluetoothSocket);

    /**
     * Envia um byte para o dispositivo conectado
     */
    public void writeByte(byte outByte);

    /**
     * Recebe um byte do dispositivo conectado
     */

    public int readByte();

}
