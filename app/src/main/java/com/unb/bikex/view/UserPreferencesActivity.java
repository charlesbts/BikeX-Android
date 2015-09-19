package com.unb.bikex.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.unb.bikex.BaseActivity;
import com.unb.bikex.R;
import com.unb.bikex.adapter.BluetoothDeviceAdapter;
import com.unb.bikex.presenter.UserPreferencesPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Charles on 9/13/2015.
 */
public class UserPreferencesActivity extends BaseActivity implements IUserPreferencesView{

    @Inject UserPreferencesPresenter userPreferencesPresenter;
    @Inject BluetoothDeviceAdapter bluetoothDeviceAdapter;
    private EditText wheelSizeEditText;
    private ListView bluetoothDeviceListView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);
        wheelSizeEditText = (EditText) findViewById(R.id.wheelSizeEditText);
        bluetoothDeviceListView = (ListView) findViewById(R.id.bluetoothDeviceListView);
    }

    @Override
    protected void onResume(){
        super.onResume();
        userPreferencesPresenter.onResume();
    }

    public void saveUserPreferences(View view){
        userPreferencesPresenter.setData(wheelSizeEditText.getText().toString());
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
    public void setItemsBluetoothDeviceListView(List<String> items){
        bluetoothDeviceAdapter.setBluetoothDeviceList(items);
        bluetoothDeviceListView.setAdapter(bluetoothDeviceAdapter);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new UserPreferencesModule(this));
    }
}
