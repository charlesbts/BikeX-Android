package com.unb.bikex.application;

import com.unb.bikex.database.DatabaseHelper;
import com.unb.bikex.model.ModelModule;
import com.unb.bikex.sharedpreferences.UserSharedPreferences;
import com.unb.bikex.wireless.BluetoothModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles on 8/6/2015.
 */
@Module(
        injects = BikeXApp.class,
        includes = {BluetoothModule.class, ModelModule.class}
)
public class BikeXAppModule {

    BikeXApp app;

    BikeXAppModule(BikeXApp app){
        this.app = app;
    }

    @Provides
    @Singleton
    public BikeXApp provideBikeXApp(){
        return app;
    }

    @Provides
    public UserSharedPreferences provideUserSharedPreferences(BikeXApp context){
        return new UserSharedPreferences(context);
    }

    @Provides
    @Singleton
    public DatabaseHelper provideDataBaseHelper(BikeXApp context){
        return new DatabaseHelper(context);
    }
}
