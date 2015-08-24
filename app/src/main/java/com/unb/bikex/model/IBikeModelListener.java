package com.unb.bikex.model;

/**
 * Created by Charles on 8/17/2015.
 */
public interface IBikeModelListener {
    void notifyBluetoothConnection();
    void notifyByteReceived(byte inByte);
}
