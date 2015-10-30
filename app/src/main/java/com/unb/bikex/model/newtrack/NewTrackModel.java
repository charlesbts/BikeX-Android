package com.unb.bikex.model.newtrack;

import com.unb.bikex.model.DataLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charles on 10/30/2015.
 */
public class NewTrackModel implements INewTrackModel {
    List<DataLocation> dataLocationList = new ArrayList<>();
    int position = 0;

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
}
