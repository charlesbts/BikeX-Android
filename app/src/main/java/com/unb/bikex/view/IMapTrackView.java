package com.unb.bikex.view;

import com.unb.bikex.model.DataLocation;

import java.util.List;

/**
 * Created by Charles on 10/20/2015.
 */
public interface IMapTrackView {
    void initMarkersMap(List<DataLocation> dataLocationList);
}
