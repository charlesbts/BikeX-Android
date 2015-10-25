package com.unb.bikex.view.track;

import com.unb.bikex.application.BikeXAppModule;
import com.unb.bikex.model.bike.BikeModel;
import com.unb.bikex.model.bike.IBikeModel;
import com.unb.bikex.model.map.IMapModel;
import com.unb.bikex.presenter.MapTrackPresenter;
import com.unb.bikex.presenter.SensorPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles on 8/16/2015.
 */
@Module(
        injects = {BikeModel.class, TrackActivity.class, SensorFragment.class, MapTrackFragment.class},
        addsTo = BikeXAppModule.class
)
public class TrackModule {
    ISensorView iSensorView;
    IMapTrackView iMapTrackView;

    public TrackModule(ISensorView iSensorView){
        this.iSensorView = iSensorView;
    }


    @Provides
    @Singleton
    public SensorPresenter provideSensorPresenter(IBikeModel iBikeModel){
        return new SensorPresenter(iSensorView, iBikeModel);
    }

    @Provides
    @Singleton
    public MapTrackPresenter provideMapTrackPresenter(IMapModel iMapModel){
        return new MapTrackPresenter(iMapTrackView, iMapModel);
    }


    public void setiSensorView(ISensorView iSensorView){
        this.iSensorView = iSensorView;
    }

    public void setiMapTrackView(IMapTrackView iMapTrackView){
        this.iMapTrackView = iMapTrackView;
    }

}
