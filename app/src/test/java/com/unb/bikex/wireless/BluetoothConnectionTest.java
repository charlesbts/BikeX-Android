package com.unb.bikex.wireless;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import com.unb.bikex.wireless.BluetoothConnection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Charles on 8/2/2015.
 */
public class BluetoothConnectionTest {
    private final String uuid = "00001101-0000-1000-8000-00805f9b34fb";
    private final String address = "30:15:01:16:03:90";
    @Mock BluetoothAdapter bluetoothAdapter;
    @Mock BluetoothDevice bluetoothDevice;
    @Mock BluetoothSocket bluetoothSocket;
    BluetoothConnection bluetoothConnection;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        bluetoothConnection = new BluetoothConnection(bluetoothAdapter);
        assertNotNull(bluetoothConnection);
    }


    @After
    public void tearDown(){
        bluetoothConnection = null;
    }
}
