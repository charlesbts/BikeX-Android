package com.unb.bikex.view.newtrack;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
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
public class NewTrackActivity extends BaseActivity implements INewTrackView, OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    @Inject NewTrackPresenter newTrackPresenter;
    private SupportMapFragment map;
    private GoogleMap googleMap;
    private GoogleApiAvailability googleApiAvailability;

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
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Track Name");
        alert.setMessage("Give a name to your track");
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(NewTrackActivity.this, input.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
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
    public void showRemoveMarkerError(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void removeMarker(){
        Marker marker;
        position--;
        marker = markerList.get(position);
        marker.remove();
    }

}
