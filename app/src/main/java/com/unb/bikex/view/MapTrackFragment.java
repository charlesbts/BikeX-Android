package com.unb.bikex.view;

import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unb.bikex.BaseActivity;
import com.unb.bikex.R;
import com.unb.bikex.model.DataLocation;
import com.unb.bikex.presenter.MapTrackPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Charles on 9/21/2015.
 */
public class MapTrackFragment extends BaseFragment implements IMapTrackView{
    @Inject MapTrackPresenter mapTrackPresenter;
    private GoogleMap map;

    @Override
    public void initPresenterView(){
        ((TrackActivity) getActivity()).sensorModule.setiMapTrackView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container, false);

        map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.location_map)).getMap();
        map.setMyLocationEnabled(true);

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        mapTrackPresenter.onResume();
    }

    @Override
    public void initMarkersMap(List<DataLocation> locationData){
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(locationData.get(0).getLatitude(), locationData.get(0).getLongitude())));
        map.moveCamera(CameraUpdateFactory.zoomTo(16));

        for(DataLocation markers : locationData){
            LatLng latLng = new LatLng(markers.getLatitude(), markers.getLongitude());
            drawMarker(latLng);
        }
    }


    private void drawMarker(LatLng point){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point);
        map.addMarker(markerOptions);
    }

    public List<DataLocation> getMock(){
        List<DataLocation> locationData = new ArrayList<>();
        DataLocation locationData1 = new DataLocation(-15.801309, -47.855670);
        DataLocation locationData2 = new DataLocation(-15.800333, -47.858748);
        DataLocation locationData3 = new DataLocation(-15.799386, -47.861690);
        DataLocation locationData4 = new DataLocation(-15.796851, -47.865767);
        locationData.add(0, locationData1);
        locationData.add(1, locationData2);
        locationData.add(2, locationData3);
        locationData.add(3, locationData4);
        return locationData;
    }


}
