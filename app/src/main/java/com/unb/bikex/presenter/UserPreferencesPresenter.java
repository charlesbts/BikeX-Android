package com.unb.bikex.presenter;

import android.util.Log;

import com.unb.bikex.R;
import com.unb.bikex.app.BikeXApp;
import com.unb.bikex.bike.Bike;
import com.unb.bikex.model.userpreferences.IUserPreferencesModel;
import com.unb.bikex.view.IUserPreferencesView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Charles on 9/13/2015.
 */
public class UserPreferencesPresenter {
    private IUserPreferencesView iUserPreferencesView;
    private IUserPreferencesModel iUserPreferencesModel;
    @Inject BikeXApp bikeXApp;

    public UserPreferencesPresenter(IUserPreferencesView iUserPreferencesView, IUserPreferencesModel iUserPreferencesModel){
        this.iUserPreferencesView = iUserPreferencesView;
        this.iUserPreferencesModel = iUserPreferencesModel;
    }

    public void onResume(){
        try {
            List<String> device = iUserPreferencesModel.getBluetoothDeviceList();
            iUserPreferencesView.setItemsBluetoothDeviceListView(device);
        }
        catch (IllegalStateException bluetoothIsNotEnable){
            iUserPreferencesView.requestBluetoothEnable(bluetoothIsNotEnable.getMessage());
        }
    }

    public void setData(String wheelSize, String bluetoothMacAddress){
        try {
            if(wheelSize.isEmpty()) {
                iUserPreferencesView.showErrorSavePreferences("Please, enter the wheel size");
            }
            else if(bluetoothMacAddress.isEmpty()){
                iUserPreferencesView.showErrorSavePreferences("Please, choose one bluetooth device");
            }
            else {
                iUserPreferencesModel.setWheelSize(Integer.parseInt(wheelSize));
                iUserPreferencesModel.setBluetoothMacAddress(bluetoothMacAddress);
                iUserPreferencesModel.save();
                iUserPreferencesView.showSuccessSavePreferences();
            }
        }
        catch (IllegalArgumentException wheelOutOfRange){
            iUserPreferencesView.showErrorSavePreferences(wheelOutOfRange.getMessage());
        }
    }

    public void onDestroy(){
        iUserPreferencesView.finishWithShowErrorBluetoothEnable();
    }
}
