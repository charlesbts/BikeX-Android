package com.unb.bikex.model.newtrack;

import com.unb.bikex.database.DatabaseHelper;
import com.unb.bikex.entity.Location;
import com.unb.bikex.entity.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charles on 10/30/2015.
 */
public class NewTrackModel implements INewTrackModel {
    DatabaseHelper databaseHelper;
    List<Location> locationList = new ArrayList<>();
    int position = 0;

    public NewTrackModel(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void addDataLocation(double latitude, double longitude){
        Location location = new Location(latitude, longitude);
        locationList.add(position, location);
        position++;
    }

    @Override
    public void removeDataLocation() throws IndexOutOfBoundsException{
        position--;
        if(position >= 0){
            locationList.remove(position);
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
            for (int position = 0; position < locationList.size(); position++) {
                databaseHelper.insertLocation(trackCod, position,
                        locationList.get(position).getLatitude(), locationList.get(position).getLongitude());
            }
        }
        else{
            throw new IllegalArgumentException();
        }
    }
}
