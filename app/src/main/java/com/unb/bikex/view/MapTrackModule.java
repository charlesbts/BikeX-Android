package com.unb.bikex.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.unb.bikex.app.BikeXApp;
import com.unb.bikex.app.BikeXAppModule;
import com.unb.bikex.model.bike.BikeModel;
import com.unb.bikex.model.bike.IBikeModel;
import com.unb.bikex.presenter.MapTrackPresenter;
import com.unb.bikex.view.IMapTrackView;
import com.unb.bikex.view.MapTrackActivity;

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
