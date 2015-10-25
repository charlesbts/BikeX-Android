package com.unb.bikex.wireless;

import android.bluetooth.BluetoothSocket;

/**
 * Created by Charles on 8/3/2015.
 */
public interface IBluetoothConnected {


    void setBluetoothSocket(BluetoothSocket bluetoothSocket);

    /**
     * Recebe um byte do dispositivo conectado
     */

    int readByte();

}
