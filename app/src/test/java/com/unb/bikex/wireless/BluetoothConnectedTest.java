package com.unb.bikex.wireless;

import android.bluetooth.BluetoothSocket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Charles on 10/25/2015.
 */
public class BluetoothConnectedTest {
    BluetoothConnected bluetoothConnected;
    @Mock BluetoothSocket bluetoothSocket;
    @Mock InputStream inputStream;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        bluetoothConnected = new BluetoothConnected();
        assertNotNull(bluetoothConnected);
    }

    @Test
    public void readTest(){
        int inByte;
        try {
            bluetoothConnected.setBluetoothSocket(bluetoothSocket);
            Mockito.when(bluetoothSocket.getInputStream()).thenReturn(inputStream);
            Mockito.when(inputStream.read()).thenReturn(41);
            inByte = bluetoothConnected.readByte();
            assertEquals(41, inByte);
        }
        catch(IOException e){

        }

    }

    @After
    public void tearDown(){
        bluetoothConnected = null;
    }
}
