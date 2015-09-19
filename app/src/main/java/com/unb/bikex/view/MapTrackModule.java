package com.unb.bikex.view;

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
        injects = {MapTrackActivity.class, BikeModel.class},
        addsTo = BikeXAppModule.class
)
public class MapTrackModule {
    IMapTrackView iMapTrackView;

    public MapTrackModule(IMapTrackView iMapTrackView){
        this.iMapTrackView = iMapTrackView;
    }

    @Provides
    @Singleton
    public IMapTrackView provideIMapTrackView(){
        return iMapTrackView;
    }

    @Provides
    @Singleton
    public MapTrackPresenter provideMapTrackPresenter(IMapTrackView iMapTrackView, IBikeModel iBikeModel){
        return new MapTrackPresenter(iMapTrackView, iBikeModel);
    }
}
