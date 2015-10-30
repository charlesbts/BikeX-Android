package com.unb.bikex.presenter;

import com.unb.bikex.model.newtrack.INewTrackModel;
import com.unb.bikex.view.newtrack.INewTrackView;

/**
 * Created by Charles on 10/30/2015.
 */
public class NewTrackPresenter {

    private INewTrackView iNewTrackView;
    private INewTrackModel iNewTrackModel;

    public NewTrackPresenter(INewTrackView iNewTrackView, INewTrackModel iNewTrackModel){
        this.iNewTrackView = iNewTrackView;
        this.iNewTrackModel = iNewTrackModel;
    }

    public void onMapClick(double latitude, double longitude){
        iNewTrackModel.addDataLocation(latitude, longitude);
        iNewTrackView.drawMarker(latitude, longitude);
    }

    public void onMapLongClick(){
        try {
            iNewTrackModel.removeDataLocation();
            iNewTrackView.removeMarker();
        }
        catch (IndexOutOfBoundsException noMarker){
            iNewTrackView.showRemoveMarkerError(noMarker.getMessage());
        }
    }
}
