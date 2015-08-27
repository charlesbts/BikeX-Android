package com.unb.bikex.model;

import com.unb.bikex.model.BluetoothModel;
import com.unb.bikex.model.IBluetoothModel;
import com.unb.bikex.model.main.IMainModel;
import com.unb.bikex.model.main.MainModel;
import com.unb.bikex.wireless.IBluetoothConnected;
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
public class ModelModule {

    @Provides
    public IMainModel provideIMainModel(){
        return new MainModel();
    }

    @Provides
    @Singleton
    public IBluetoothModel provideIBikeModel(IBluetoothConnection iBluetoothConnection, IBluetoothConnected iBluetoothConnected){
        return new BluetoothModel(iBluetoothConnection, iBluetoothConnected);
    }
}
