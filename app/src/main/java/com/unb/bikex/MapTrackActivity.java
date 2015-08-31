package com.unb.bikex;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.unb.bikex.presenter.MapTrackPresenter;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;


public class MapTrackActivity extends BaseActivity implements IMapTrackView{
    private Button startTrackButton;
    private TextView speedTextView;
    private TextView cadenceTextView;
    private TextView distanceTextView;
    private Chronometer chronometer;
    private ProgressDialog bluetoothConnectionProgressDialog;
    @Inject MapTrackPresenter mapTrackPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_track);
        startTrackButton = (Button) findViewById(R.id.startTrack);
        speedTextView = (TextView) findViewById(R.id.speed);
        cadenceTextView = (TextView) findViewById(R.id.cadence);
        distanceTextView = (TextView) findViewById(R.id.distance);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
    }


    public void startTrack(View view){
        chronometer.setBase(SystemClock.elapsedRealtime());
        mapTrackPresenter.onResume();
    }

    @Override
    public void requestBluetoothEnable(String bluetoothEnable){
        startActivityForResult(new Intent(bluetoothEnable), 42);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 42 && resultCode == RESULT_OK){
            mapTrackPresenter.getBluetoothConnection();
        }
        else if(requestCode == 42 && resultCode == RESULT_CANCELED){
            mapTrackPresenter.onDestroy();
        }

    }

    @Override
    public void showBluetoothConnectionProgressDialog(){
        bluetoothConnectionProgressDialog = new ProgressDialog(this);
        bluetoothConnectionProgressDialog.setMessage(getResources().getString(R.string.bluetooth_connection));
        bluetoothConnectionProgressDialog.show();
    }


    @Override
    public void hideBluetoothConnectionProgressDialog(){
        bluetoothConnectionProgressDialog.dismiss();
    }

    @Override
    public void showErrorBluetoothConnection(){
        Toast.makeText(MapTrackActivity.this, R.string.bluetooth_connection_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccessBluetoothConnection(String nameDevice){
        Toast.makeText(MapTrackActivity.this, getString(R.string.bluetooth_connection_success) + nameDevice, Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishWithShowErrorBluetoothEnable(){
        Toast.makeText(MapTrackActivity.this, R.string.bluetooth_enable_error, Toast.LENGTH_LONG).show();
        finish();
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
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new MapTrackModule(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map_track, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
