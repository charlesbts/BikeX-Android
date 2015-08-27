package com.unb.bikex.bike;

import com.unb.bikex.model.IBluetoothModel;
import com.unb.bikex.wireless.IBluetoothConnected;

/**
 * Created by Charles on 8/15/2015.
 */
public class Bike {
    private IBluetoothConnected iBluetoothConnected;
    private IBluetoothModel iBikeModel;
    private int speed;
    private int cadency;

    public Bike(IBluetoothConnected iBluetoothConnected, IBluetoothModel iBikeModel){

    }
}
