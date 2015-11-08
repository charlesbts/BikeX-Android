package com.unb.bikex.view.userpreferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.unb.bikex.view.BaseActivity;
import com.unb.bikex.R;
import com.unb.bikex.adapter.BluetoothDeviceAdapter;
import com.unb.bikex.presenter.UserPreferencesPresenter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Charles on 9/13/2015.
 */
public class UserPreferencesActivity extends BaseActivity implements IUserPreferencesView {

    @Inject UserPreferencesPresenter userPreferencesPresenter;
    @Inject BluetoothDeviceAdapter bluetoothDeviceAdapter;
    private EditText wheelSizeEditText;
    private EditText desiredCadenceEditText;
    private ListView bluetoothDeviceListView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);
        wheelSizeEditText = (EditText) findViewById(R.id.wheelSizeEditText);
        desiredCadenceEditText = (EditText) findViewById(R.id.desired_cadence);
        bluetoothDeviceListView = (ListView) findViewById(R.id.bluetoothDeviceListView);
    }

    @Override
    protected void onResume(){
        super.onResume();
        userPreferencesPresenter.onResume();
    }

    public void saveUserPreferences(View view){
        String bluetoothMacAddress = bluetoothDeviceAdapter.getBluetoothMacAddress();
        String wheelSize = wheelSizeEditText.getText().toString();
        String desiredCadence = desiredCadenceEditText.getText().toString();
        userPreferencesPresenter.setData(wheelSize, desiredCadence, bluetoothMacAddress);
    }

    @Override
    public void showSuccessSavePreferences(){
        Toast.makeText(UserPreferencesActivity.this, getString(R.string.preferences_update_success), Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void showErrorSavePreferences(String message){
        Toast.makeText(UserPreferencesActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setItemsBluetoothDeviceListView(List<String> items, String defaultMacAddress){
        bluetoothDeviceAdapter.setBluetoothDeviceList(items);
        bluetoothDeviceAdapter.setDefaultMacAddress(defaultMacAddress);
        bluetoothDeviceListView.setAdapter(bluetoothDeviceAdapter);
    }

    @Override
    public void setWheelSizeEditText(String wheelSize){
        wheelSizeEditText.setText(wheelSize);
    }

    @Override
    public void setDesiredCadenceEditText(String desiredCadence){
        desiredCadenceEditText.setText(desiredCadence);
    }

    @Override
    public void requestBluetoothEnable(String bluetoothEnable){
        startActivityForResult(new Intent(bluetoothEnable), 42);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 42 && resultCode == RESULT_OK){
            userPreferencesPresenter.onResume();
        }
        else if(requestCode == 42 && resultCode == RESULT_CANCELED){
            userPreferencesPresenter.onDestroy();
        }

    }

    @Override
    public void finishWithShowErrorBluetoothEnable(){
        Toast.makeText(UserPreferencesActivity.this, R.string.bluetooth_enable_error, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new UserPreferencesModule(this));
    }
}
