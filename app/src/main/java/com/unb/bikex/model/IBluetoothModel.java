package com.unb.bikex.model;

import com.unb.bikex.presenter.IBluetoothListener;

/**
 * Created by Charles on 8/15/2015.
 */
public interface IBluetoothModel {

    void setPresenterListener(IBluetoothListener listener);
    String getBluetoothEnable();
    void getBluetoothConnection();
}
