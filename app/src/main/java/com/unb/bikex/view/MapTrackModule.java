package com.unb.bikex.view;

import com.unb.bikex.app.BikeXAppModule;
import com.unb.bikex.model.bike.BikeModel;
import com.unb.bikex.model.bike.IBikeModel;
import com.unb.bikex.presenter.MapTrackPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles on 8/16/2015.
 */
@Module(
        injects = {MapTrackActivity.class, BikeModel.class, TrackActivity.class, SensorFragment.class},
        addsTo = BikeXAppModule.class
)
public class MapTrackModule {
    IMapTrackView iMapTrackView;

    public MapTrackModule(IMapTrackView iMapTrackView){
        this.iMapTrackView = iMapTrackView;
    }


    @Provides
    @Singleton
    public MapTrackPresenter provideMapTrackPresenter( IBikeModel iBikeModel){
        return new MapTrackPresenter(iMapTrackView, iBikeModel);
    }


    public void setiMapTrackView(IMapTrackView iMapTrackView){
        this.iMapTrackView = iMapTrackView;
    }

}
