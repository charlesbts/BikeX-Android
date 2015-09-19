package com.unb.bikex.presenter;

import com.unb.bikex.model.userpreferences.IUserPreferencesModel;
import com.unb.bikex.view.IUserPreferencesView;

import java.util.ArrayList;
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
        //TODO: Mock de dispositivos emparelhados

        List<String> device = new ArrayList<>();
        device.add("HC-05" + "\n" + "01:02:03:04:05:06");
        device.add("HC-06" + "\n" + "AA:BB:CC:DD:EE:FF");
        device.add("HC-07" + "\n" + "BB:UU:CC:EE:TT:AA");
        iUserPreferencesView.setItemsBluetoothDeviceListView(device);
    }

    public void setData(String wheelSize){
        try {
            if(wheelSize.isEmpty()) {
                iUserPreferencesView.showErrorSavePreferences("The field must have some value");
            }
            else {
                iUserPreferencesModel.setWheelSize(Integer.parseInt(wheelSize));
                iUserPreferencesModel.setBluetoothMacAddress("30:15:01:16:03:90");
                iUserPreferencesModel.save();
                iUserPreferencesView.showSuccessSavePreferences();
            }
        }
        catch (IllegalArgumentException wheelOutOfRange){
            iUserPreferencesView.showErrorSavePreferences(wheelOutOfRange.getMessage());
        }
    }
}
