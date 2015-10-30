package com.unb.bikex.view.newtrack;

import com.unb.bikex.application.BikeXAppModule;
import com.unb.bikex.model.newtrack.INewTrackModel;
import com.unb.bikex.presenter.NewTrackPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles on 10/29/2015.
 */
@Module(
        injects = NewTrackActivity.class,
        addsTo = BikeXAppModule.class
)
public class NewTrackModule {
    INewTrackView iNewTrackView;

    public NewTrackModule(INewTrackView iNewTrackView){
        this.iNewTrackView = iNewTrackView;
    }

    @Provides
    public INewTrackView provideINewTrackView(){
        return iNewTrackView;
    }

    @Provides
    public NewTrackPresenter provideNewTrackPresenter(INewTrackView iNewTrackView, INewTrackModel iNewTrackModel){
        return new NewTrackPresenter(iNewTrackView, iNewTrackModel);
    }

}
