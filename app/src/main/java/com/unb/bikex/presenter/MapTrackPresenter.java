package com.unb.bikex.presenter;

import com.unb.bikex.model.DataLocation;
import com.unb.bikex.view.IMapTrackView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charles on 10/20/2015.
 */
public class MapTrackPresenter {
    IMapTrackView iMapTrackView;

    public MapTrackPresenter(IMapTrackView iMapTrackView){
        this.iMapTrackView = iMapTrackView;
    }

    public void onResume(){
        List<DataLocation> locationData = new ArrayList<>();
        DataLocation locationData1 = new DataLocation(-15.801309, -47.855670);
        DataLocation locationData2 = new DataLocation(-15.800333, -47.858748);
        DataLocation locationData3 = new DataLocation(-15.799386, -47.861690);
        DataLocation locationData4 = new DataLocation(-15.796851, -47.865767);
        locationData.add(0, locationData1);
        locationData.add(1, locationData2);
        locationData.add(2, locationData3);
        locationData.add(3, locationData4);
        iMapTrackView.initMarkersMap(locationData);
    }
}
