package com.unb.bikex.bike;

import com.unb.bikex.model.bike.IBikeModel;
import com.unb.bikex.wireless.IBluetoothConnected;

/**
 * Created by Charles on 8/15/2015.
 */
public class Bike {
    private IBluetoothConnected iBluetoothConnected;
    private IBikeModel iBikeModel;
    private int speed;
    private int cadency;

    public Bike(IBluetoothConnected iBluetoothConnected, IBikeModel iBikeModel){

    }
}
