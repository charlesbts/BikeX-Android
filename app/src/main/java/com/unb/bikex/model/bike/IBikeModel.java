package com.unb.bikex.model.bike;

import com.unb.bikex.presenter.IBikeListener;

/**
 * Created by Charles on 8/15/2015.
 */
public interface IBikeModel {

    void setPresenterListener(IBikeListener listener);
    String getBluetoothEnable();
    void getBluetoothConnection();
    void getBluetoothDisconnection();
    void readForever();
    void prepareUserDependency() throws NullPointerException;
}
