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
        iMapTrackView.moveCamera(dataLocationList.get(0).getLatitude(), dataLocationList.get(0).getLongitude(), 16);
        iMapTrackView.drawGreenMarker(dataLocationList.get(0).getLatitude(), dataLocationList.get(0).getLongitude());

        for(int i = 1; i < dataLocationList.size(); i++){
            iMapTrackView.drawRedMarker(dataLocationList.get(i).getLatitude(), dataLocationList.get(i).getLongitude());
        }
    }
}
