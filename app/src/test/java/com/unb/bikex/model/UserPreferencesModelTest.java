package com.unb.bikex.model;

import com.unb.bikex.application.BikeXApp;
import com.unb.bikex.model.bike.BikeModel;
import com.unb.bikex.model.userpreferences.UserPreferencesModel;
import com.unb.bikex.sharedpreferences.UserSharedPreferences;
import com.unb.bikex.wireless.IBluetoothSetup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Charles on 10/24/2015.
 */
public class UserPreferencesModelTest {
    UserPreferencesModel userPreferencesModel;
    @Mock
    UserSharedPreferences userSharedPreferences;
    @Mock
    IBluetoothSetup iBluetoothSetup;


    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        userPreferencesModel = new UserPreferencesModel(userSharedPreferences, iBluetoothSetup);
        assertNotNull(userPreferencesModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWheelSize0TestException(){
        userPreferencesModel.setWheelSize(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWheelSize17TestException(){
        userPreferencesModel.setWheelSize(17);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWheelSize34TestException(){
        userPreferencesModel.setWheelSize(34);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setWheelSize99TestException(){
        userPreferencesModel.setWheelSize(99);
    }

    @Test(expected = IllegalStateException.class)
    public void getBluetoothDeviceListTestException(){
        Mockito.when(iBluetoothSetup.enable()).thenReturn("Algo");
        userPreferencesModel.getBluetoothDeviceList();
    }

    @Test
    public void getBluetoothDeviceListTest(){
        List<String> pairedDevicesMock = new ArrayList<>();
        pairedDevicesMock.add(0, "Bt1");
        pairedDevicesMock.add(1, "Bt2");
        pairedDevicesMock.add(2, "Bt3");
        Mockito.when(iBluetoothSetup.enable()).thenReturn(null);
        Mockito.when(iBluetoothSetup.getPairedDeviceList()).thenReturn(pairedDevicesMock);
        assertEquals(pairedDevicesMock, userPreferencesModel.getBluetoothDeviceList());
    }

    @After
    public void tearDown(){
        userPreferencesModel = null;
    }
}
