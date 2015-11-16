package com.unb.bikex.view.main;

import com.unb.bikex.entity.Track;

import java.util.List;

/**
 * Created by Charles on 8/4/2015.
 */
public interface IMainView {
    void setItemsTrackListView(List<Track> items);
    void refreshListView(long cod);
    void showErrorDeleteTrack();
}
