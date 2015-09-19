package com.unb.bikex.view;

import java.util.List;

/**
 * Created by Charles on 9/13/2015.
 */
public interface IUserPreferencesView {
    void showSuccessSavePreferences();
    void showErrorSavePreferences(String message);
    void setItemsBluetoothDeviceListView(List<String> items);
}
