package com.unb.bikex.presenter;


import com.unb.bikex.entity.Location;
import com.unb.bikex.model.map.IMapModel;
import com.unb.bikex.view.track.IMapTrackView;

import java.util.List;

/**
 * Created by Charles on 10/20/2015.
 */
public class MapTrackPresenter {
    private IMapTrackView iMapTrackView;
    private IMapModel iMapModel;
    private boolean isFirst = true;

    public MapTrackPresenter(IMapTrackView iMapTrackView, IMapModel iMapModel){
        this.iMapTrackView = iMapTrackView;
        this.iMapModel = iMapModel;
    }

    public void onResume(long extras){
        if(isFirst) {
            List<Location> locationList = iMapModel.getDataLocationList(extras);
            iMapTrackView.moveCamera(locationList.get(0).getLatitude(), locationList.get(0).getLongitude());
            updateMarks(locationList);
            isFirst = false;
        }
    }

    private void updateMarks(List<Location> locationList){
        if(!locationList.isEmpty()) {
            for (int i = 0; i < locationList.size(); i++) {
                iMapTrackView.drawRedMarker(locationList.get(i).getLatitude(), locationList.get(i).getLongitude());
            }
            iMapTrackView.changeColorInitialMarker();
        }
    }

    public void onMyLocationChange(double latitude, double longitude){
        iMapTrackView.moveCamera(latitude, longitude);
        if(iMapModel.checkDataLocation(latitude, longitude)){
            iMapTrackView.removeInitialMarker();
            if(iMapModel.getDataLocationPosition() != IMapModel.LAST_MARKER)
                iMapTrackView.changeColorInitialMarker();
            if(iMapModel.getDataLocationPosition() == IMapModel.FIRST_MARKER){
                iMapTrackView.notifyFirstMarkerAchieve();
            }
            else if(iMapModel.getDataLocationPosition() == IMapModel.LAST_MARKER){
                iMapTrackView.notifyLastMarkerAchieve();
            }
        }
    }
}
