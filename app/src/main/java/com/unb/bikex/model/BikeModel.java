package com.unb.bikex.model;

import android.util.Log;

import com.unb.bikex.threadutils.ICallbackThread;
import com.unb.bikex.presenter.IMapTrackPresenterListener;
import com.unb.bikex.wireless.IBluetoothConnection;

import javax.inject.Inject;

/**
 * Created by Charles on 8/15/2015.
 */
public class BikeModel implements IBikeModel, ICallbackThread {

    private IBluetoothConnection iBluetoothConnection;
    private IMapTrackPresenterListener iMapTrackPresenterListener;

    @Inject
    public BikeModel(IBluetoothConnection iBluetoothConnection){
        this.iBluetoothConnection = iBluetoothConnection;
    }

    @Override
    public void setPresenterListener(IMapTrackPresenterListener listener){
        this.iMapTrackPresenterListener = listener;
    }

    @Override
    public String getBluetoothEnable(){
        return iBluetoothConnection.enable();
    }

    @Override
    public void getBluetoothConnection(){
        iBluetoothConnection.setListener(this);
        iBluetoothConnection.connectToDevice("30:15:01:16:03:90");
    }

    @Override
    public void notifyListener(){
        Log.d("BikeModel", "notifyThreadOver");
        if(!iBluetoothConnection.isConnected()){
            iMapTrackPresenterListener.setErrorBluetoothConnection();
        }
        else {
            iMapTrackPresenterListener.setSuccessBluetoothConnection(iBluetoothConnection.getDeviceName());
        }
    }



}
