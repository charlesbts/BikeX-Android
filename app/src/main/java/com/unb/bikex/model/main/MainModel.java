package com.unb.bikex.model.main;

import com.unb.bikex.database.DatabaseHelper;
import java.util.List;

/**
 * Created by Charles on 8/4/2015.
 */
public class MainModel implements IMainModel {
    DatabaseHelper databaseHelper;

    public MainModel(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    @Override
    public List<Track> getTrackList(){
        List<Track> trackList;

        databaseHelper.deleteAllTracks();

        databaseHelper.insertTrack(new Track(0, "Tcu"));
        databaseHelper.insertTrack(new Track (0, "UnB"));
        databaseHelper.insertTrack(new Track (0, "Brasília"));

        trackList = databaseHelper.selectAllTracks();
        return trackList;
    }

}
