package com.unb.bikex.view.newtrack;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unb.bikex.R;
import com.unb.bikex.presenter.NewTrackPresenter;
import com.unb.bikex.view.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Charles on 10/20/2015.
 */
public class NewTrackActivity extends BaseActivity implements INewTrackView, OnMapReadyCallback, GoogleMap.OnMapClickListener,
        GoogleMap.OnMapLongClickListener, DialogInterface.OnShowListener  {

    @Inject NewTrackPresenter newTrackPresenter;
    private SupportMapFragment map;
    private GoogleMap googleMap;
    private GoogleApiAvailability googleApiAvailability;

    EditText trackNameEditText;
    AlertDialog alert;

    private List<Marker> markerList = new ArrayList<>();
    int position = 0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_track);
        googleApiAvailability = GoogleApiAvailability.getInstance();
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.location_map));
        map.getMapAsync(this);

    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new NewTrackModule(this));
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if(googleApiAvailability.isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS) {
            googleMap = map;
            setupMap();
        }
    }

    private void setupMap(){
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMapClickListener(this);
        googleMap.setOnMapLongClickListener(this);
    }

    public void saveTrack(View view){
        trackNameEditText = new EditText(this);
        alert = new AlertDialog.Builder(this)
                .setTitle(R.string.track_name)
                .setMessage(R.string.track_name_instruction)
                .setView(trackNameEditText)
                .setPositiveButton(R.string.track_name_dialog_positive_button, null)
                .setNegativeButton(R.string.track_name_dialog_negative_button, null)
                .create();
        alert.setOnShowListener(this);
        alert.show();
    }

    @Override
    public void onMapClick(LatLng latLng){
        newTrackPresenter.onMapClick(latLng.latitude, latLng.longitude);
    }

    @Override
    public void onMapLongClick(LatLng latLng){
        newTrackPresenter.onMapLongClick();
    }


    @Override
    public void onShow(DialogInterface dialog){
        Button saveButton = alert.getButton(AlertDialog.BUTTON_POSITIVE);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTrackPresenter.saveNewTrack(trackNameEditText.getText().toString());
            }
        });
    }

    @Override
    public void drawMarker(double latitude, double longitude){
        Marker marker;
        LatLng latLng = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        marker = googleMap.addMarker(markerOptions);
        markerList.add(position, marker);
        position++;
    }

    @Override
    public void showTrackNameNullError(){
        String error = getString(R.string.new_track_error_null_message);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(error);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, error.length(), 0);
        trackNameEditText.setError(spannableStringBuilder);
    }

    @Override
    public void showTrackNameExistsError(){
        String error = getString(R.string.new_track_error_exists_message);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(error);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, error.length(), 0);
        trackNameEditText.setError(spannableStringBuilder);
    }

    @Override
    public void showRemoveMarkerError(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSaveSuccess(){
        alert.dismiss();
        Toast.makeText(this, R.string.new_track_success_message, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void removeMarker(){
        Marker marker;
        position--;
        marker = markerList.get(position);
        marker.remove();
    }

}
