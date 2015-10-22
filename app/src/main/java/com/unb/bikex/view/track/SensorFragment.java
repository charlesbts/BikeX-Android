package com.unb.bikex.view.track;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.unb.bikex.R;
import com.unb.bikex.presenter.SensorPresenter;
import com.unb.bikex.view.BaseFragment;
import com.unb.bikex.view.userpreferences.UserPreferencesActivity;

import javax.inject.Inject;

/**
 * Created by Charles on 9/22/2015.
 */
public class SensorFragment extends BaseFragment implements ISensorView, View.OnLongClickListener{

    @Inject
    SensorPresenter sensorPresenter;
    private Button startTrackButton;
    private TextView speedTextView;
    private TextView cadenceTextView;
    private TextView distanceTextView;
    private Chronometer chronometer;
    private ProgressDialog bluetoothConnectionProgressDialog;
    private boolean isTrackOnFlag = false;


    @Override
    public void initPresenterView(){
        ((TrackActivity) getActivity()).trackModule.setiSensorView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_map_track, container, false);
        Log.d("SensorFragment", "onCreateView");

        startTrackButton = (Button) view.findViewById(R.id.startTrack);
        speedTextView = (TextView) view.findViewById(R.id.speed);
        cadenceTextView = (TextView) view.findViewById(R.id.cadence);
        distanceTextView = (TextView) view.findViewById(R.id.distance);
        chronometer = (Chronometer) view.findViewById(R.id.chronometer);

        startTrackButton.setOnLongClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        sensorPresenter.getBluetoothDisconnection();
        Log.d("SensorFragment", "onDestroyView");
    }

    @Override
    public void requestBluetoothEnable(String bluetoothEnable){
        startActivityForResult(new Intent(bluetoothEnable), 42);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 42 && resultCode == AppCompatActivity.RESULT_OK){
            sensorPresenter.getBluetoothConnection();
        }
        else if(requestCode == 42 && resultCode == AppCompatActivity.RESULT_CANCELED){
            sensorPresenter.onDestroy();
        }

    }

    @Override
    public void showBluetoothConnectionProgressDialog(){
        bluetoothConnectionProgressDialog = new ProgressDialog(getActivity());
        bluetoothConnectionProgressDialog.setMessage(getResources().getString(R.string.bluetooth_connection));
        bluetoothConnectionProgressDialog.show();
    }


    @Override
    public void hideBluetoothConnectionProgressDialog(){
        bluetoothConnectionProgressDialog.dismiss();
    }

    @Override
    public void showErrorBluetoothConnection(){
        Toast.makeText(getActivity(), R.string.bluetooth_connection_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessBluetoothConnection(String nameDevice){
        Toast.makeText(getActivity(), getString(R.string.bluetooth_connection_success) + nameDevice, Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishWithShowErrorBluetoothEnable(){
        Toast.makeText(getActivity(), R.string.bluetooth_enable_error, Toast.LENGTH_LONG).show();
        getActivity().finish();
    }

    @Override
    public void refreshSpeed(String inByte){
        speedTextView.setText(inByte);
    }

    @Override
    public void hideStartTrackButton(){
        startTrackButton.setVisibility(View.GONE);
    }

    @Override
    public void refreshCadence(String inByte){
        cadenceTextView.setText(inByte);
    }

    @Override
    public void refreshDistance(String inByte){
        distanceTextView.setText(inByte);
    }

    @Override
    public void startTrack(){
        isTrackOnFlag = true;
        startTrackButton.setText(R.string.stop_track_button_name);
        chronometer.start();
    }

    @Override
    public void startUserPreferencesActivity(){
        Toast.makeText(getActivity(), getString(R.string.preferences_update_message), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), UserPreferencesActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View view){
        if(!isTrackOnFlag) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            sensorPresenter.onResume();
        }
        else{
            sensorPresenter.getBluetoothDisconnection();
            chronometer.stop();
            isTrackOnFlag = false;
            startTrackButton.setText(R.string.start_track_button_name);
        }
        return true;
    }

}
