package com.unb.bikex.view.main;

import android.app.Activity;

import com.unb.bikex.adapter.TrackAdapter;
import com.unb.bikex.app.BikeXAppModule;
import com.unb.bikex.model.main.IMainModel;
import com.unb.bikex.presenter.MainPresenter;
import com.unb.bikex.view.main.IMainView;
import com.unb.bikex.view.main.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles on 8/7/2015.
 */
@Module(
        injects = MainActivity.class,
        addsTo = BikeXAppModule.class
)
public class MainModule {
    IMainView iMainView;

    public MainModule(IMainView iMainView){
        this.iMainView = iMainView;
    }


    @Provides
    //@Singleton
    public IMainView provideIMainView(){
        return iMainView;
    }

    @Provides
    public TrackAdapter provideTrackAdapter(IMainView iMainView){
        return new TrackAdapter((Activity) iMainView);
    }

    @Provides
    //@Singleton
    public MainPresenter provideMainPresenter(IMainView iMainView, IMainModel iMainModel){
        return new MainPresenter(iMainView, iMainModel);
    }
}
