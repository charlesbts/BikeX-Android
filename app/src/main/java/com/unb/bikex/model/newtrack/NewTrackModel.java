package com.unb.bikex.model.newtrack;

import com.unb.bikex.database.DatabaseHelper;
import com.unb.bikex.model.DataLocation;
import com.unb.bikex.model.main.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charles on 10/30/2015.
 */
public class NewTrackModel implements INewTrackModel {
    DatabaseHelper databaseHelper;
    List<DataLocation> dataLocationList = new ArrayList<>();
    int position = 0;

    public NewTrackModel(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void addDataLocation(double latitude, double longitude){
        DataLocation dataLocation = new DataLocation(latitude, longitude);
        dataLocationList.add(position, dataLocation);
        position++;
    }

    @Override
    public void removeDataLocation() throws IndexOutOfBoundsException{
        position--;
        if(position >= 0){
            dataLocationList.remove(position);
        }
        else{
            position = 0;
            throw new IndexOutOfBoundsException("There's no marker in map");
        }
    }

    @Override
    public void persistTrack(String trackName) throws IllegalArgumentException{
        Track track = new Track(0, trackName);
        long trackCod;

        if(!databaseHelper.trackNameExists(trackName)) {
            trackCod = databaseHelper.insertTrack(track);
            for (int position = 0; position < dataLocationList.size(); position++) {
                databaseHelper.insertLocation(trackCod, position,
                        dataLocationList.get(position).getLatitude(), dataLocationList.get(position).getLongitude());
            }
        }
        else{
            throw new IllegalArgumentException();
        }
    }
}
