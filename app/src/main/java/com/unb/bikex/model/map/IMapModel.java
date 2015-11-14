package com.unb.bikex.model.map;

import com.unb.bikex.model.DataLocation;

import java.util.List;

/**
 * Created by Charles on 10/22/2015.
 */
public interface IMapModel{
    int FIRST_MARKER = 0;
    int LAST_MARKER = -1;

    List<DataLocation> getDataLocationList(long trackCod);
    boolean checkDataLocation(double latitude, double longitude);
    int getDataLocationPosition();
}
