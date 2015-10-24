package com.unb.bikex.model;

import com.unb.bikex.app.BikeXApp;
import com.unb.bikex.model.bike.BikeModel;
import com.unb.bikex.model.bike.IBikeModel;
import com.unb.bikex.model.main.IMainModel;
import com.unb.bikex.model.main.MainModel;
import com.unb.bikex.model.map.IMapModel;
import com.unb.bikex.model.map.MapModel;
import com.unb.bikex.model.userpreferences.IUserPreferencesModel;
import com.unb.bikex.model.userpreferences.UserPreferencesModel;
import com.unb.bikex.sharedpreferences.UserSharedPreferences;
import com.unb.bikex.wireless.IBluetoothConnected;
import com.unb.bikex.wireless.IBluetoothConnection;
import com.unb.bikex.wireless.IBluetoothSetup;

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
    public IMainModel provideIMainModel(BikeXApp context){
        return new MainModel(context);
    }

    @Provides
    @Singleton
    public IBikeModel provideIBikeModel(IBluetoothConnection iBluetoothConnection, IBluetoothConnected iBluetoothConnected,
                                        UserSharedPreferences userSharedPreferences){
        return new BikeModel(iBluetoothConnection, iBluetoothConnected,  userSharedPreferences);
    }

    @Provides
    public IUserPreferencesModel provideIUserPreferencesModel(UserSharedPreferences userSharedPreferences,
                                                              IBluetoothSetup iBluetoothSetup){
        return  new UserPreferencesModel(userSharedPreferences, iBluetoothSetup);
    }

    @Provides
    public IMapModel provideIMapModel(){
        return new MapModel();
    }
}
