package com.unb.bikex.view.userpreferences;

import android.app.Activity;

import com.unb.bikex.adapter.BluetoothDeviceAdapter;
import com.unb.bikex.app.BikeXAppModule;
import com.unb.bikex.model.userpreferences.IUserPreferencesModel;
import com.unb.bikex.presenter.UserPreferencesPresenter;
import com.unb.bikex.sharedpreferences.UserSharedPreferences;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Charles on 9/13/2015.
 */
@Module(
        injects = {UserPreferencesActivity.class, UserSharedPreferences.class},
        addsTo = BikeXAppModule.class
)
public class UserPreferencesModule {
    IUserPreferencesView iUserPreferencesView;

    public UserPreferencesModule(IUserPreferencesView iUserPreferencesView){
        this.iUserPreferencesView = iUserPreferencesView;
    }

    @Provides
    public IUserPreferencesView provideIUserPreferencesView(){
        return iUserPreferencesView;
    }

    @Provides
    public BluetoothDeviceAdapter provideBluetoothDeviceAdapter(){
        return new BluetoothDeviceAdapter((Activity) iUserPreferencesView);
    }

    @Provides
    public UserPreferencesPresenter provideUserPreferencesPresenter(IUserPreferencesView iUserPreferencesView,
                                                                    IUserPreferencesModel iUserPreferencesModel){
        return new UserPreferencesPresenter(iUserPreferencesView, iUserPreferencesModel);
    }
}
