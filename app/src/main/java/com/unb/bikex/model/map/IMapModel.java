package com.unb.bikex.model.map;

import com.unb.bikex.model.DataLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charles on 10/22/2015.
 */
public interface IMapModel{
    public List<DataLocation> getDataLocationList(long trackCod);
}
