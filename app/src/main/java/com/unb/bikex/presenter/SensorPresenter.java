package com.unb.bikex.presenter;

import com.unb.bikex.model.bike.IBikeModel;
import com.unb.bikex.view.track.ISensorView;

/**
 * Created by Charles on 8/16/2015.
 */
public class SensorPresenter implements IBikeListener {
    ISensorView iSensorView;
    IBikeModel iBikeModel;

    public SensorPresenter(ISensorView iSensorView, IBikeModel iBikeModel){
        this.iSensorView = iSensorView;
        this.iBikeModel = iBikeModel;
    }

    public void onResume(){
        try {
            iBikeModel.prepareUserDependency();
            String bluetoothEnable = iBikeModel.getBluetoothEnable();
            if (bluetoothEnable != null) {
                iSensorView.requestBluetoothEnable(bluetoothEnable);
            }
            else {
                getBluetoothConnection();
            }
        }
        catch (NullPointerException sharedPreferences){
            iSensorView.startUserPreferencesActivity();
        }

    }

    public void getBluetoothConnection(){
        iSensorView.showBluetoothConnectionProgressDialog();
        iBikeModel.setPresenterListener(this);
        iBikeModel.getBluetoothConnection();
    }

    public void getBluetoothDisconnection(){
        iBikeModel.getBluetoothDisconnection();
    }


    @Override
    public void setErrorBluetoothConnection(){
        iSensorView.hideBluetoothConnectionProgressDialog();
        iSensorView.showErrorBluetoothConnection();
    }

    @Override
    public void setSuccessBluetoothConnection(String deviceName){
        iSensorView.hideBluetoothConnectionProgressDialog();
        iSensorView.showSuccessBluetoothConnection(deviceName);
        iSensorView.startTrack();
        iBikeModel.readForever();
    }

    @Override
    public void refreshSpeedView(float speed){
        iSensorView.refreshSpeed(String.format("%.1f", speed));
    }

    @Override
    public void refreshCadenceView(float cadence){
        iSensorView.refreshCadence(String.format("%.1f", cadence));
    }

    @Override
    public void refreshDistanceView(float distance){
        iSensorView.refreshDistance(String.format("%.1f", distance));
    }

    public void onDestroy(){
        iSensorView.finishWithShowErrorBluetoothEnable();
    }

}
