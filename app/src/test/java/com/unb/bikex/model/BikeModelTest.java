package com.unb.bikex.model;

import com.unb.bikex.application.BikeXApp;
import com.unb.bikex.model.bike.BikeModel;
import com.unb.bikex.sharedpreferences.UserSharedPreferences;
import com.unb.bikex.wireless.BluetoothConnected;
import com.unb.bikex.wireless.BluetoothConnection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Charles on 9/13/2015.
 */
public class BikeModelTest {
    BikeModel bikeModel;
    @Mock BluetoothConnected bluetoothConnected;
    @Mock BluetoothConnection bluetoothConnection;
    @Mock BikeXApp context;
    @Mock UserSharedPreferences userSharedPreferences;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        bikeModel = new BikeModel(bluetoothConnection, bluetoothConnected, userSharedPreferences);
        assertNotNull(bikeModel);
    }

    @Test
    public void calculateCadenceTest(){
        float cadence;
        try {
            Method calculateCadence = bikeModel.getClass().getDeclaredMethod("calculateCadence", int.class);
            calculateCadence.setAccessible(true);
            cadence = (float) calculateCadence.invoke(bikeModel, 4096);
            assertEquals(cadence, 60.0, 0.1);
            cadence = (float) calculateCadence.invoke(bikeModel, 3097);
            assertEquals(cadence, 79.3, 0.1);
            cadence = (float) calculateCadence.invoke(bikeModel, 0);
            assertEquals(cadence, 0.0, 0.1);
            cadence = (float) calculateCadence.invoke(bikeModel, 32768);
            assertEquals(cadence, 0.0, 0.1);
            cadence = (float) calculateCadence.invoke(bikeModel, 65535);
            assertEquals(cadence, 3.7, 0.1);
        }
        catch (Exception e){

        }
    }

    @Test
    public void calculateSpeedTest(){
        float speed;
        try {
            Method calculateSpeed = bikeModel.getClass().getDeclaredMethod("calculateSpeed", int.class, int.class);
            calculateSpeed.setAccessible(true);
            speed = (float) calculateSpeed.invoke(bikeModel, 18, 4096);
            assertEquals(speed, 5.1, 0.1);
            speed = (float) calculateSpeed.invoke(bikeModel, 18, 2048);
            assertEquals(speed, 10.3, 0.1);
            speed = (float) calculateSpeed.invoke(bikeModel, 25, 0);
            assertEquals(speed, 0.0, 0.1);
            speed = (float) calculateSpeed.invoke(bikeModel, 25, 32768);
            assertEquals(speed, 0.0, 0.1);
            speed = (float) calculateSpeed.invoke(bikeModel, 33, 65535);
            assertEquals(speed, 0.5, 0.1);
        }
        catch (Exception e){

        }
    }

    @Test
    public void handleInfoTest(){
        int result;

        try {
            Method handleInfo = bikeModel.getClass().getDeclaredMethod("handleInfo", int.class, int.class);
            handleInfo.setAccessible(true);
            result  = (int) handleInfo.invoke(bikeModel, 1, 1);
            assertEquals(257, result);
            result = (int) handleInfo.invoke(bikeModel, 0 , 0);
            assertEquals(0, result);
            result = (int) handleInfo.invoke(bikeModel, 255, 255);
            assertEquals(65535, result);
        }
        catch (Exception e){

        }
    }

    @After
    public void tearDown() throws Exception{
        bikeModel = null;
    }
}
