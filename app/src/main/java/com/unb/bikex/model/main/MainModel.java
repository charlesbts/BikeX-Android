package com.unb.bikex.model.main;

import com.unb.bikex.model.main.IMainModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charles on 8/4/2015.
 */
public class MainModel implements IMainModel {

    @Override
    public List<String > getTrackList(){
        /* TODO: Implementar persistência */
        List<String> trackList = new ArrayList<>();
        trackList.add("Parque");
        trackList.add("UnB");
        trackList.add("Buritis");
        return trackList;
    }

}
