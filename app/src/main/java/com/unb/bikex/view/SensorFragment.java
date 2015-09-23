package com.unb.bikex.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.style.BulletSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.unb.bikex.BaseActivity;
import com.unb.bikex.R;
import com.unb.bikex.presenter.MapTrackPresenter;
import com.unb.bikex.view.userpreferences.UserPreferencesActivity;

import javax.inject.Inject;

/**
 * Created by Charles on 9/22/2015.
 */
public class SensorFragment extends BaseFragment implements IMapTrackView{

    @Inject MapTrackPresenter mapTrackPresenter;

    private Button startTrackButton;
    private TextView speedTextView;
    private TextView cadenceTextView;
    private TextView distanceTextView;
    private Chronometer chronometer;
    private ProgressDialog bluetoothConnectionProgressDialog;


    @Override
    public void initPresenterView(){
        ((TrackActivity) getActivity()).mapTrackModule.setiMapTrackView(this);
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

        startTrackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                mapTrackPresenter.onResume();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        mapTrackPresenter.getBluetoothDisconnection();
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
            mapTrackPresenter.getBluetoothConnection();
        }
        else if(requestCode == 42 && resultCode == AppCompatActivity.RESULT_CANCELED){
            mapTrackPresenter.onDestroy();
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
    public void startChronometer(){
        chronometer.start();
    }

    @Override
    public void startUserPreferencesActivity(){
        Toast.makeText(getActivity(), getString(R.string.preferences_update_message), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), UserPreferencesActivity.class);
        startActivity(intent);
    }

}
