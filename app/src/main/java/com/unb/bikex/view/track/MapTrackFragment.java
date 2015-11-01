package com.unb.bikex.view.track;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unb.bikex.R;
import com.unb.bikex.presenter.MapTrackPresenter;
import com.unb.bikex.view.BaseFragment;
import com.unb.bikex.view.main.MainActivity;

import javax.inject.Inject;

/**
 * Created by Charles on 9/21/2015.
 */
public class MapTrackFragment extends BaseFragment implements IMapTrackView, GoogleMap.OnMyLocationChangeListener {

    @Inject MapTrackPresenter mapTrackPresenter;
    private GoogleMap map;
    private GoogleApiAvailability googleApiAvailability;
    private long trackCod;


    @Override
    public void initPresenterView(){
        ((TrackActivity) getActivity()).trackModule.setiMapTrackView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container, false);

        map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.location_map)).getMap();
        googleApiAvailability = GoogleApiAvailability.getInstance();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstance){
        super.onActivityCreated(savedInstance);
        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null){
            trackCod = extras.getLong(MainActivity.COD_TRACK);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(googleApiAvailability.isGooglePlayServicesAvailable(getActivity()) == ConnectionResult.SUCCESS) {
            setupMap();
            mapTrackPresenter.onResume(trackCod);
        }
    }

    @Override
    public void moveCamera(double latitude, double longitude, float zoom){
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));
        map.moveCamera(CameraUpdateFactory.zoomTo(zoom));
    }

    @Override
    public void drawRedMarker(double latitude, double longitude){
        LatLng latLng = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        map.addMarker(markerOptions);
    }

    @Override
    public void drawGreenMarker(double latitude, double longitude){
        LatLng latLng = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        map.addMarker(markerOptions);
    }

    private void setupMap(){
        map.setMyLocationEnabled(true);
        map.setOnMyLocationChangeListener(this);
    }

    @Override
    public void onMyLocationChange(Location location){
        /* TODO: Chama presenter e move camera */
    }


}
