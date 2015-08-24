package com.unb.bikex.presenter;

import com.unb.bikex.IMapTrackView;
import com.unb.bikex.R;
import com.unb.bikex.model.IBikeModel;

/**
 * Created by Charles on 8/16/2015.
 */
public class MapTrackPresenter implements IMapTrackPresenterListener{
    IMapTrackView iMapTrackView;
    IBikeModel iBikeModel;

    public MapTrackPresenter(IMapTrackView iMapTrackView, IBikeModel iBikeModel){
        this.iMapTrackView = iMapTrackView;
        this.iBikeModel = iBikeModel;
    }

    public void onResume(){
        String bluetoothEnable = iBikeModel.getBluetoothEnable();
        if(bluetoothEnable != null) {
            iMapTrackView.requestBluetoothEnable(bluetoothEnable);
        }
        else{
            getBluetoothConnection();
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
    }

    public void onDestroy(){
        iMapTrackView.finishWithShowErrorBluetoothEnable();
    }

}
