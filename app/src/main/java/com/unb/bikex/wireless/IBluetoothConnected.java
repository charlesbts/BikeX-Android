package com.unb.bikex.wireless;

/**
 * Created by Charles on 8/3/2015.
 */
public interface IBluetoothConnected {

    /**
     * Envia um byte para o dispositivo conectado
     */
    public void writeByte(byte outByte);

    /**
     * Rece um byte do dispositivo conectado
     */

    public void readByte();
}
