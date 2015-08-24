package com.unb.bikex.model;

import com.unb.bikex.wireless.IBluetoothConnection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles on 8/7/2015.
 */
@Module(
        library = true,
        complete = false
)
public class MainModelModule {

    @Provides
    public IMainModel provideIMainModel(){
        return new MainModel();
    }

    @Provides
    @Singleton
    public IBikeModel provideIBikeModel(IBluetoothConnection iBluetoothConnection){
        return new BikeModel(iBluetoothConnection);
    }
}
