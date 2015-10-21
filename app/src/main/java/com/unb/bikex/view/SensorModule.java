package com.unb.bikex.view;

import android.util.Log;

import com.unb.bikex.app.BikeXAppModule;
import com.unb.bikex.model.bike.BikeModel;
import com.unb.bikex.model.bike.IBikeModel;
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
public class SensorModule {
    ISensorView iSensorView;
    IMapTrackView iMapTrackView;

    public SensorModule(ISensorView iSensorView){
        this.iSensorView = iSensorView;
    }


    @Provides
    @Singleton
    public SensorPresenter provideSensorPresenter(IBikeModel iBikeModel){
        Log.d("INJECTED", "SENSORPRESENT");
        return new SensorPresenter(iSensorView, iBikeModel);
    }

    @Provides
    @Singleton
    public MapTrackPresenter provideMapTrackPresenter(){
        Log.d("INJECTED", "MAPPRESENTER");
        return new MapTrackPresenter(iMapTrackView);
    }


    public void setiSensorView(ISensorView iSensorView){
        this.iSensorView = iSensorView;
    }

    public void setiMapTrackView(IMapTrackView iMapTrackView){
        this.iMapTrackView = iMapTrackView;
    }

}
