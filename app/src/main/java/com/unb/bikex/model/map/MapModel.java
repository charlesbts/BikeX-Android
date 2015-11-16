package com.unb.bikex.model.map;



import com.unb.bikex.database.DatabaseHelper;
import com.unb.bikex.entity.Location;
import java.util.List;

/**
 * Created by Charles on 10/22/2015.
 */
public class MapModel implements IMapModel {
    private DatabaseHelper databaseHelper;
    private List<Location> locationDataList;
    private int dataLocationPosition = -1;
    private boolean isFirstCall = true;

    public MapModel(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public List<Location> getDataLocationList(long trackCod) {
        if(isFirstCall) {
            locationDataList = databaseHelper.selectAllDataLocationsFromTrack(trackCod);
            isFirstCall = false;
        }
        return locationDataList;
    }


    @Override
    public boolean checkDataLocation(double latitude, double longitude){
        if(!locationDataList.isEmpty()){
            if (isTheSameLocation(latitude, locationDataList.get(0).getLatitude()) &&
                    isTheSameLocation(longitude, locationDataList.get(0).getLongitude())) {
                locationDataList.remove(0);
                dataLocationPosition++;
                return true;
            }
        }
        return false;
    }

    @Override
    public int getDataLocationPosition(){
        if(locationDataList.isEmpty()){
            return IMapModel.LAST_MARKER;
        }
        return dataLocationPosition;
    }

    private boolean isTheSameLocation(double l1, double l2) {
        final double LATITUDE_LONGITUDE_ERROR = 0.000030;
        return (l1 >= l2 - LATITUDE_LONGITUDE_ERROR && l1 <= l2 + LATITUDE_LONGITUDE_ERROR);
    }
}
