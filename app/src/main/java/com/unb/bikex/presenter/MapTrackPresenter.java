package com.unb.bikex.presenter;

import com.unb.bikex.view.IMapTrackView;
import com.unb.bikex.model.bike.IBikeModel;

/**
 * Created by Charles on 8/16/2015.
 */
public class MapTrackPresenter implements IBikeListener {
    IMapTrackView iMapTrackView;
    IBikeModel iBikeModel;

    public MapTrackPresenter(IMapTrackView iMapTrackView, IBikeModel iBikeModel){
        this.iMapTrackView = iMapTrackView;
        this.iBikeModel = iBikeModel;
    }

    public void onResume(){
        try {
            iBikeModel.prepareUserDependency();
            String bluetoothEnable = iBikeModel.getBluetoothEnable();
            if (bluetoothEnable != null) {
                iMapTrackView.requestBluetoothEnable(bluetoothEnable);
            }
            else {
                getBluetoothConnection();
            }
        }
        catch (NullPointerException sharedPreferences){
            iMapTrackView.startUserPreferencesActivity();
        }

    }

    public void getBluetoothConnection(){
        iMapTrackView.showBluetoothConnectionProgressDialog();
        iBikeModel.setPresenterListener(this);
        iBikeModel.getBluetoothConnection();
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
        iBikeModel.readForever();
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
