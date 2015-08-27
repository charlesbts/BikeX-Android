package com.unb.bikex;

import com.unb.bikex.model.BluetoothModel;
import com.unb.bikex.model.IBluetoothModel;
import com.unb.bikex.presenter.MapTrackPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles on 8/16/2015.
 */
@Module(
        injects = {MapTrackActivity.class, BluetoothModel.class},
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
    public MapTrackPresenter provideMapTrackPresenter(IMapTrackView iMapTrackView, IBluetoothModel iBikeModel){
        return new MapTrackPresenter(iMapTrackView, iBikeModel);
    }
}
