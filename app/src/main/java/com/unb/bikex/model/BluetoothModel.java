package com.unb.bikex.model;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.unb.bikex.threadutils.ICallbackThread;
import com.unb.bikex.presenter.IBluetoothListener;
import com.unb.bikex.wireless.IBluetoothConnected;
import com.unb.bikex.wireless.IBluetoothConnection;

import javax.inject.Inject;

/**
 * Created by Charles on 8/15/2015.
 */
public class BluetoothModel implements IBluetoothModel, ICallbackThread {

    private final int ID_SPEED_SENSOR = 13;
    private final int ID_CADENCE_SENSOR = 42;
    private final double CONVERT_INCH_TO_CENTIMETER = 2.54;
    private final double CONVERT_METER_TO_KILOMETER = 3.6;
    private final int CONVERT_CENTIMETER_TO_METER = 100;
    private final int CONVERT_SECOND_TO_MINUTE = 60;
    private final int FREQUENCY = 4096;
    private final int FLAG_OVERFLOW = 32768;

    private IBluetoothConnection iBluetoothConnection;
    private IBluetoothConnected iBluetoothConnected;
    private IBluetoothListener iBluetoothListener;
    private int byte1, byte2, byte3;

    @Inject
    public BluetoothModel(IBluetoothConnection iBluetoothConnection, IBluetoothConnected iBluetoothConnected){
        this.iBluetoothConnection = iBluetoothConnection;
        this.iBluetoothConnected = iBluetoothConnected;
    }

    @Override
    public void setPresenterListener(IBluetoothListener listener){
        this.iBluetoothListener = listener;
    }

    @Override
    public String getBluetoothEnable(){
        return iBluetoothConnection.enable();
    }

    @Override
    public void getBluetoothConnection(){
        iBluetoothConnection.setListener(this);
        iBluetoothConnection.connectToDevice("30:15:01:16:03:90");
    }

    @Override
    public void notifyListener(final int threadId){
        handleThreadCallback();
    }

    private void handleThreadCallback(){
        if (!iBluetoothConnection.isConnected()) {
            iBluetoothListener.setErrorBluetoothConnection();
        }
        else {
            iBluetoothListener.setSuccessBluetoothConnection(iBluetoothConnection.getDeviceName());
            iBluetoothConnected.setBluetoothSocket(iBluetoothConnection.getBluetoothSocket());
            readForever();
        }
    }


    private void readForever(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (byte1 != ID_SPEED_SENSOR && byte1 != ID_CADENCE_SENSOR) { /* Synchronize */
                    byte1 = iBluetoothConnected.readByte();
                }
                while(true){
                    byte2 = iBluetoothConnected.readByte();
                    byte3 = iBluetoothConnected.readByte();
                    if (byte1 == ID_SPEED_SENSOR){
                        float speed = calculateSpeed(33, handleInfo(byte2, byte3));
                        updateUi(ID_SPEED_SENSOR, speed);
                    }
                    else if(byte1 == ID_CADENCE_SENSOR){
                        float cadence = calculateCadence(handleInfo(byte2, byte3));
                        updateUi(ID_CADENCE_SENSOR, cadence);
                    }
                    byte1 = iBluetoothConnected.readByte();
                }
            }
        }).start();
    }

    private void updateUi(final int idSensor, final float value){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                switch (idSensor){
                    case ID_SPEED_SENSOR:
                        iBluetoothListener.refreshSpeedView(value);
                        break;
                    case ID_CADENCE_SENSOR:
                        iBluetoothListener.refreshCadenceView(value);
                        break;
                    default:
                        /* Do nothing */
                        break;
                }
            }
        });
    }


    private int handleInfo(int msb, int lsb){
        int packet = 0;
        packet |= msb;
        packet = packet << 8;
        packet |= lsb;
        return packet;
    }

    private float calculateCadence(int packet){
        return  (float) (CONVERT_SECOND_TO_MINUTE*FREQUENCY)/packet;
    }

    private float calculateSpeed(int aroSize, int packet){
        return (float) (aroSize * CONVERT_INCH_TO_CENTIMETER * Math.PI * FREQUENCY * CONVERT_METER_TO_KILOMETER) /
                (packet * CONVERT_CENTIMETER_TO_METER);
    }


}
