package com.unb.bikex.presenter;


import com.unb.bikex.model.DataLocation;
import com.unb.bikex.model.map.IMapModel;
import com.unb.bikex.view.track.IMapTrackView;

import java.util.List;

/**
 * Created by Charles on 10/20/2015.
 */
public class MapTrackPresenter {
    IMapTrackView iMapTrackView;
    IMapModel iMapModel;

    public MapTrackPresenter(IMapTrackView iMapTrackView, IMapModel iMapModel){
        this.iMapTrackView = iMapTrackView;
        this.iMapModel = iMapModel;
    }

    public void onResume(long extras){
        List<DataLocation> dataLocationList = iMapModel.getDataLocationList(extras);
        iMapTrackView.moveCamera(dataLocationList.get(0).getLatitude(), dataLocationList.get(0).getLongitude());
        updateMarks(dataLocationList);
    }

    private void updateMarks(List<DataLocation> dataLocationList){
        if(!dataLocationList.isEmpty()) {
            for (int i = 0; i < dataLocationList.size(); i++) {
                iMapTrackView.drawRedMarker(dataLocationList.get(i).getLatitude(), dataLocationList.get(i).getLongitude());
            }
            iMapTrackView.changeColorInitialMarker();
        }
    }

    public void onMyLocationChange(double latitude, double longitude){
        iMapTrackView.moveCamera(latitude, longitude);
        try {
            if(iMapModel.checkDataLocation(latitude, longitude)) {
                iMapTrackView.removeInitialMarker();
                iMapTrackView.changeColorInitialMarker();
            }
        }
        catch(Exception emptyList){

        }
    }
}
