package com.unb.bikex.wirelesstest;

import android.bluetooth.BluetoothAdapter;

import com.unb.bikex.wireless.BluetoothSetup;
import com.unb.bikex.wireless.IBluetoothSetup;

import junit.framework.TestCase;

/**
 * Created by Charles on 7/28/2015.
 */


public class BluetoothSetupTest extends TestCase{

    BluetoothAdapter bluetoothAdapter;
    IBluetoothSetup iBluetoothSetup;

    @Override
    public void setUp() throws Exception{
        super.setUp();
        iBluetoothSetup = new BluetoothSetup(BluetoothAdapter.getDefaultAdapter());

    }

    public void testBluetoothSetupExists(){
        assertNotNull(iBluetoothSetup);
    }

    public void testEnable(){
        /* Bluetooth on */
        String isEnable;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothAdapter.enable();
        while(!bluetoothAdapter.isEnabled()){
            try{
                Thread.sleep(100L);
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }

        }
        isEnable = iBluetoothSetup.enable();
        assertNull(isEnable);

        /* Bluetooth off */
        bluetoothAdapter.disable();
        while(bluetoothAdapter.isEnabled()){
            try{
                Thread.sleep(100L);
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return;
            }

        }
        isEnable = iBluetoothSetup.enable();
        assertEquals(BluetoothAdapter.ACTION_REQUEST_ENABLE, isEnable);
    }

    @Override
    public void tearDown() throws Exception{
        super.tearDown();
    }
}
