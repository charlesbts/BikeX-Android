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

    public void onResume(){
        List<DataLocation> dataLocationList = iMapModel.getDataLocationList();
        iMapTrackView.moveCamera(dataLocationList.get(0).getLatitude(), dataLocationList.get(0).getLongitude(), 16);
        for(DataLocation markers : dataLocationList){
            iMapTrackView.drawMarker(markers.getLatitude(), markers.getLongitude());
        }
    }
}
