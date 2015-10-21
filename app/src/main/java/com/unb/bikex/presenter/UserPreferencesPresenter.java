package com.unb.bikex.presenter;

import com.unb.bikex.model.userpreferences.IUserPreferencesModel;
import com.unb.bikex.view.userpreferences.IUserPreferencesView;

import java.util.List;

/**
 * Created by Charles on 9/13/2015.
 */
public class UserPreferencesPresenter {
    private IUserPreferencesView iUserPreferencesView;
    private IUserPreferencesModel iUserPreferencesModel;

    public UserPreferencesPresenter(IUserPreferencesView iUserPreferencesView, IUserPreferencesModel iUserPreferencesModel){
        this.iUserPreferencesView = iUserPreferencesView;
        this.iUserPreferencesModel = iUserPreferencesModel;
    }

    public void onResume(){
        try {
            int wheelSize;
            String bluetoothMacAddress;
            List<String> device = iUserPreferencesModel.getBluetoothDeviceList();
            bluetoothMacAddress = iUserPreferencesModel.getBluetoothMacAddress();
            iUserPreferencesView.setItemsBluetoothDeviceListView(device, bluetoothMacAddress);
            wheelSize = iUserPreferencesModel.getWheelSize();
            if(wheelSize != 0) {
                iUserPreferencesView.setWheelSizeEditText(Integer.toString(wheelSize));
            }
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
