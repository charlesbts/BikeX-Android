package com.unb.bikex.view.statistic;

import com.unb.bikex.application.BikeXAppModule;
import com.unb.bikex.model.statistic.IStatisticModel;
import com.unb.bikex.presenter.StatisticPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles on 11/16/2015.
 */
@Module(
        injects = StatisticActivity.class,
        addsTo = BikeXAppModule.class
)
public class StatisticModule {
    private IStatisticView iStatisticView;

    public StatisticModule(IStatisticView iStatisticView){
        this.iStatisticView = iStatisticView;
    }

    @Provides
    public IStatisticView provideIStatisticView(){
        return iStatisticView;
    }

    @Provides
    @Singleton
    public StatisticPresenter provideStatisticPresenter(IStatisticView iStatisticView, IStatisticModel iStatisticModel){
        return new StatisticPresenter(iStatisticView, iStatisticModel);
    }

}
