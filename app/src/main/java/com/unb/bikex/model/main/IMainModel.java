package com.unb.bikex.model.main;

import com.unb.bikex.entity.Track;

import java.util.List;

/**
 * Created by Charles on 8/4/2015.
 */
public interface IMainModel {

    List<Track> getTrackList();
    void deleteTrack(long cod) throws Exception;
}
