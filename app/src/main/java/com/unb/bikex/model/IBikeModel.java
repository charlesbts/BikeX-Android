package com.unb.bikex.model;

import com.unb.bikex.presenter.IMapTrackPresenterListener;

/**
 * Created by Charles on 8/15/2015.
 */
public interface IBikeModel {

    void setPresenterListener(IMapTrackPresenterListener listener);
    String getBluetoothEnable();
    void getBluetoothConnection();
}
