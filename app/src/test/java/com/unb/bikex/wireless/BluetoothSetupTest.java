package com.unb.bikex.wireless;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Charles on 7/30/2015.
 */

public class BluetoothSetupTest {
    @Mock BluetoothAdapter bluetoothAdapter;
    @Mock BluetoothDevice bluetoothDevice1;
    @Mock BluetoothDevice bluetoothDevice2;
    IBluetoothSetup iBluetoothSetup;
    @Spy Set<BluetoothDevice> bluetoothDeviceSet = new HashSet<BluetoothDevice>() {};

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        iBluetoothSetup = new BluetoothSetup(bluetoothAdapter);
        assertNotNull(iBluetoothSetup);
    }

    @Test
    public void enableTest(){
        Mockito.when(bluetoothAdapter.isEnabled()).thenReturn(true);
        assertNull(iBluetoothSetup.enable());
        Mockito.when(bluetoothAdapter.isEnabled()).thenReturn(false);
        assertEquals(BluetoothAdapter.ACTION_REQUEST_ENABLE, iBluetoothSetup.enable());
    }

    @Test
    public void getPairedDeviceListTest(){
        Mockito.when(bluetoothDeviceSet.size()).thenReturn(2);
        Mockito.when(bluetoothDevice1.getName()).thenReturn("Teste");
        Mockito.when(bluetoothDevice1.getAddress()).thenReturn("00:11:22:33:AA:BB");
        bluetoothDeviceSet.add(bluetoothDevice1);
        Mockito.when(bluetoothDevice2.getName()).thenReturn("Teste2");
        Mockito.when(bluetoothDevice2.getAddress()).thenReturn("00:11:22:33:AA:CC");
        bluetoothDeviceSet.add(bluetoothDevice2);
        Mockito.when(bluetoothAdapter.getBondedDevices()).thenReturn(bluetoothDeviceSet);
        assertEquals(2, iBluetoothSetup.getPairedDeviceList().size());
        assertTrue(iBluetoothSetup.getPairedDeviceList().contains("Teste" + "\n" + "00:11:22:33:AA:BB"));
        assertTrue(iBluetoothSetup.getPairedDeviceList().contains("Teste2" + "\n" + "00:11:22:33:AA:CC"));
        bluetoothDeviceSet.clear();
        assertTrue(iBluetoothSetup.getPairedDeviceList().isEmpty());
    }

    @After
    public void tearDown() throws Exception{
        iBluetoothSetup = null;
    }

}
