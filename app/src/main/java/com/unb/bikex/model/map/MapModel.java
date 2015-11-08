package com.unb.bikex.model.map;



import com.unb.bikex.database.DatabaseHelper;
import com.unb.bikex.model.DataLocation;
import java.util.List;

/**
 * Created by Charles on 10/22/2015.
 */
public class MapModel implements IMapModel {
    private DatabaseHelper databaseHelper;
    private List<DataLocation> locationDataList;

    public MapModel(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public List<DataLocation> getDataLocationList(long trackCod) {
        locationDataList = databaseHelper.selectAllDataLocationsFromTrack(trackCod);
        return locationDataList;
    }


    @Override
    public boolean checkDataLocation(double latitude, double longitude) throws IndexOutOfBoundsException{
        if (isTheSameLocation(latitude, locationDataList.get(0).getLatitude()) &&
                isTheSameLocation(longitude, locationDataList.get(0).getLongitude())) {
            if(!locationDataList.isEmpty()) {
                locationDataList.remove(0);
                return true;
            }
            else{
                throw new IndexOutOfBoundsException();
            }
        }
        return false;
    }

    private boolean isTheSameLocation(double l1, double l2) {
        final double LATITUDE_LONGITUDE_ERROR = 0.000030;
        return (l1 >= l2 - LATITUDE_LONGITUDE_ERROR && l1 <= l2 + LATITUDE_LONGITUDE_ERROR);
    }
}
