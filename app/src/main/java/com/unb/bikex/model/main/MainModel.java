package com.unb.bikex.model.main;

import com.unb.bikex.app.BikeXApp;
import com.unb.bikex.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charles on 8/4/2015.
 */
public class MainModel implements IMainModel {
    DatabaseHelper db;
    BikeXApp context;

    public MainModel(BikeXApp context){
        this.context = context;
    }

    @Override
    public List<String > getTrackList(){
        List<String> trackList;
        /* TODO: Implementar persist�ncia
        List<String> trackList = new ArrayList<>();
        trackList.add("Parque");
        trackList.add("UnB");
        trackList.add("Buritis");
        return trackList; */

        db = new DatabaseHelper(context);
        db.insertTrack("Tcu");
        db.insertTrack("UnB");
        db.insertTrack("Bras�lia");

        trackList = db.selectAllTracks();
        return trackList;
    }

}
