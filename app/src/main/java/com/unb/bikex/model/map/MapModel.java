package com.unb.bikex.model.map;


import com.unb.bikex.database.DatabaseHelper;
import com.unb.bikex.model.DataLocation;
import java.util.List;

/**
 * Created by Charles on 10/22/2015.
 */
public class MapModel implements IMapModel{
    DatabaseHelper databaseHelper;

    public MapModel(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    @Override
    public List<DataLocation> getDataLocationList(long trackCod){
        List<DataLocation> locationDataList;
        locationDataList = databaseHelper.selectAllDataLocationsFromTrack(trackCod);
        return locationDataList;
    }
}
