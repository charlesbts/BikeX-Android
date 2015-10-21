package com.unb.bikex.view;

import com.unb.bikex.app.BikeXAppModule;
import com.unb.bikex.presenter.MapTrackPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles on 10/20/2015.
 *//*
@Module(
        injects = {TrackActivity.class, MapTrackFragment.class},
        addsTo = BikeXAppModule.class
)
public class MapTrackModule {
    IMapTrackView iMapTrackView;

    public MapTrackModule(IMapTrackView iMapTrackView){
        this.iMapTrackView = iMapTrackView;
    }

/*
    @Provides
    @Singleton
    public MapTrackPresenter provideMapTrackPresenter(){
        return new MapTrackPresenter(iMapTrackView);
    }


    public void setiMapTrackView(IMapTrackView iMapTrackView){
        this.iMapTrackView = iMapTrackView;
    }
}
*/