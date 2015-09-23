package com.unb.bikex.model.bike;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.unb.bikex.app.BikeXApp;
import com.unb.bikex.model.userpreferences.IUserPreferencesModel;
import com.unb.bikex.sharedpreferences.UserSharedPreferences;
import com.unb.bikex.threadutils.ICallbackThread;
import com.unb.bikex.presenter.IBikeListener;
import com.unb.bikex.wireless.IBluetoothConnected;
import com.unb.bikex.wireless.IBluetoothConnection;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by Charles on 8/15/2015.
 */
public class BikeModel implements IBikeModel, ICallbackThread {

    private final int ID_SPEED_SENSOR = 13;
    private final int ID_CADENCE_SENSOR = 42;
    private final int ID_DISTANCE_SENSOR = 24;
    private final double CONVERT_INCH_TO_CENTIMETER = 2.54;
    private final double CONVERT_METER_TO_KILOMETER = 3.6;
    private final int CONVERT_CENTIMETER_TO_METER = 100;
    private final int CONVERT_SECOND_TO_MINUTE = 60;
    private final int FREQUENCY = 4096;
    private final int FLAG_OVERFLOW = 32768;
    private float DISTANCE_CONSTANT;

    private IBluetoothConnection iBluetoothConnection;
    private String bluetoothMacAddress;
    private int wheelSize;
    private IBluetoothConnected iBluetoothConnected;
    private UserSharedPreferences userSharedPreferences;
    private IBikeListener iBikeListener;
    private int byte1, byte2, byte3;
    private float speed = 0, cadence = 0, distance = 0;

    @Inject
    public BikeModel(IBluetoothConnection iBluetoothConnection, IBluetoothConnected iBluetoothConnected,
                     UserSharedPreferences userSharedPreferences){
        this.iBluetoothConnection = iBluetoothConnection;
        this.iBluetoothConnected = iBluetoothConnected;
        this.userSharedPreferences = userSharedPreferences;
    }

    @Override
    public void setPresenterListener(IBikeListener listener){
        this.iBikeListener = listener;
    }

    @Override
    public String getBluetoothEnable(){
        return iBluetoothConnection.enable();
    }

    @Override
    public void prepareUserDependency() throws NullPointerException{
        bluetoothMacAddress = userSharedPreferences.retrieveBluetoothMacAddress();
        wheelSize = userSharedPreferences.retrieveWheelSize();
        DISTANCE_CONSTANT = (float) (wheelSize * CONVERT_INCH_TO_CENTIMETER * Math.PI)/(CONVERT_CENTIMETER_TO_METER * 1000);
        if(bluetoothMacAddress == null){
            throw new NullPointerException();
        }
    }

    @Override
    public void getBluetoothConnection(){
        iBluetoothConnection.setListener(this);
        iBluetoothConnection.connectToDevice(bluetoothMacAddress);
    }

    @Override
    public void getBluetoothDisconnection(){
        try {
            iBluetoothConnection.disconnectToDevice();
        }
        catch (IOException e){

        }
    }

    @Override
    public void notifyListener(final int threadId){
        handleThreadCallback();
    }

    private void handleThreadCallback(){
        if (!iBluetoothConnection.isConnected()) {
            iBikeListener.setErrorBluetoothConnection();
        }
        else {
            iBikeListener.setSuccessBluetoothConnection(iBluetoothConnection.getDeviceName());
            iBluetoothConnected.setBluetoothSocket(iBluetoothConnection.getBluetoothSocket());
        }
    }

    @Override
    public void readForever(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (byte1 != ID_SPEED_SENSOR && byte1 != ID_CADENCE_SENSOR) { /* Synchronize */
                    byte1 = iBluetoothConnected.readByte();
                }
                while(true){
                    byte2 = iBluetoothConnected.readByte();
                    byte3 = iBluetoothConnected.readByte();
                    updateModel();
                    byte1 = iBluetoothConnected.readByte();
                }
            }
        }).start();
    }

    private void updateModel(){
        int packet;
        packet = handleInfo(byte2, byte3);
        if (byte1 == ID_SPEED_SENSOR){
            speed = calculateSpeed(wheelSize, packet);
            distance++;
            updateUi(ID_SPEED_SENSOR, speed);
            updateUi(ID_DISTANCE_SENSOR, distance * DISTANCE_CONSTANT);
        }
        else if(byte1 == ID_CADENCE_SENSOR){
            cadence = calculateCadence(packet);
            updateUi(ID_CADENCE_SENSOR, cadence);
        }
    }

    private void updateUi(final int idSensor, final float value){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                switch (idSensor){
                    case ID_SPEED_SENSOR:
                        iBikeListener.refreshSpeedView(value);
                        break;
                    case ID_CADENCE_SENSOR:
                        iBikeListener.refreshCadenceView(value);
                        break;
                    case ID_DISTANCE_SENSOR:
                        iBikeListener.refreshDistanceView(value);
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
        if(packet != FLAG_OVERFLOW && packet != 0)
            return  (float) (CONVERT_SECOND_TO_MINUTE*FREQUENCY)/packet;
        else
            return cadence;
    }

    private float calculateSpeed(int wheelSize, int packet){
        if(packet != FLAG_OVERFLOW && packet != 0)
            return (float) (wheelSize * CONVERT_INCH_TO_CENTIMETER * Math.PI * FREQUENCY * CONVERT_METER_TO_KILOMETER) /
                (packet * CONVERT_CENTIMETER_TO_METER);
        else
            return speed;
    }


}
