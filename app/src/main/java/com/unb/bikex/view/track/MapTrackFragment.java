package com.unb.bikex.view.track;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.unb.bikex.R;
import com.unb.bikex.presenter.MapTrackPresenter;
import com.unb.bikex.view.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Charles on 9/21/2015.
 */
public class MapTrackFragment extends BaseFragment implements IMapTrackView {

    @Inject MapTrackPresenter mapTrackPresenter;
    private GoogleMap map;


    @Override
    public void initPresenterView(){
        ((TrackActivity) getActivity()).trackModule.setiMapTrackView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container, false);

        map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.location_map)).getMap();
        map.setMyLocationEnabled(true);
        map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                Toast.makeText(getActivity(), Double.toString(location.getLatitude()), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), Double.toString(location.getLongitude()), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        mapTrackPresenter.onResume();
    }

    @Override
    public void moveCamera(double latitude, double longitude, float zoom){
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));
        map.moveCamera(CameraUpdateFactory.zoomTo(zoom));
    }

    @Override
    public void drawMarker(double latitude, double longitude){
        LatLng latLng = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        map.addMarker(markerOptions);
    }


}
