package com.unb.bikex.presenter;

import com.unb.bikex.IMapTrackView;
import com.unb.bikex.model.IBluetoothModel;

/**
 * Created by Charles on 8/16/2015.
 */
public class MapTrackPresenter implements IBluetoothListener {
    IMapTrackView iMapTrackView;
    IBluetoothModel iBluetoothModel;

    public MapTrackPresenter(IMapTrackView iMapTrackView, IBluetoothModel iBluetoothModel){
        this.iMapTrackView = iMapTrackView;
        this.iBluetoothModel = iBluetoothModel;
    }

    public void onResume(){
        String bluetoothEnable = iBluetoothModel.getBluetoothEnable();
        if(bluetoothEnable != null) {
            iMapTrackView.requestBluetoothEnable(bluetoothEnable);
        }
        else{
            getBluetoothConnection();
        }

    }

    public void getBluetoothConnection(){
        iMapTrackView.showBluetoothConnectionProgressDialog();
        iBluetoothModel.setPresenterListener(this);
        iBluetoothModel.getBluetoothConnection();
    }


    @Override
    public void setErrorBluetoothConnection(){
        iMapTrackView.hideBluetoothConnectionProgressDialog();
        iMapTrackView.showErrorBluetoothConnection();
    }

    @Override
    public void setSuccessBluetoothConnection(String deviceName){
        iMapTrackView.hideBluetoothConnectionProgressDialog();
        iMapTrackView.showSuccessBluetoothConnection(deviceName);
        iMapTrackView.startChronometer();
    }

    @Override
    public void refreshSpeedView(float speed){
        iMapTrackView.refreshSpeed(String.format("%.1f", speed));
    }

    @Override
    public void refreshCadenceView(float cadence){
        iMapTrackView.refreshCadence(String.format("%.1f", cadence));
    }

    @Override
    public void refreshDistanceView(float distance){
        iMapTrackView.refreshDistance(String.format("%.1f", distance));
    }

    public void onDestroy(){
        iMapTrackView.finishWithShowErrorBluetoothEnable();
    }

}
