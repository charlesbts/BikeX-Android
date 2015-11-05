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
        trackList = databaseHelper.selectAllTracks();
        return trackList;
    }

    @Override
    public void deleteTrack(long cod) throws Exception{
        if(databaseHelper.deleteTrack(cod) == 0){
            throw new Exception();
        }
    }

}
