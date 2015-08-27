package com.unb.bikex;

import com.unb.bikex.model.ModelModule;
import com.unb.bikex.wireless.BluetoothModule;

import dagger.Module;

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
}
