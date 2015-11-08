package com.unb.bikex.view.track;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unb.bikex.R;
import com.unb.bikex.presenter.MapTrackPresenter;
import com.unb.bikex.view.BaseFragment;
import com.unb.bikex.view.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Charles on 9/21/2015.
 */
public class MapTrackFragment extends BaseFragment implements IMapTrackView, GoogleMap.OnMyLocationChangeListener {

    @Inject MapTrackPresenter mapTrackPresenter;
    private GoogleMap map;
    private MapEvents mapEventsCallBack;
    private GoogleApiAvailability googleApiAvailability;
    private long trackCod;
    private List<Marker> markerList = new ArrayList<>();


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
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            mapEventsCallBack = (MapEvents) activity;
        }
        catch (ClassCastException mapEventsNotImplemented){
            Log.d("MapEvents", "MapEvents not implemented in activity");
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
    public void moveCamera(double latitude, double longitude){
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));
    }

    @Override
    public void drawRedMarker(double latitude, double longitude){
        Marker marker;
        LatLng latLng = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        marker = map.addMarker(markerOptions);
        markerList.add(marker);
    }

    @Override
    public void changeColorInitialMarker(){
        Marker marker;
        marker = markerList.get(0);
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        markerList.set(0, marker);
    }

    @Override
    public void removeInitialMarker(){
        Marker marker;
        marker = markerList.get(0);
        marker.remove();
        markerList.remove(0);
    }

    @Override
    public void notifyFirstMarkerAchieve(){
        mapEventsCallBack.onFirstMarkerAchieve();
    }

    private void setupMap(){
        map.getUiSettings().setScrollGesturesEnabled(false);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMyLocationEnabled(true);
        map.setOnMyLocationChangeListener(this);
    }

    @Override
    public void onMyLocationChange(Location location){
        mapTrackPresenter.onMyLocationChange(location.getLatitude(), location.getLongitude());
    }

    @Override
    public void onDetach(){
        mapEventsCallBack = null;
        super.onDetach();
    }


}
