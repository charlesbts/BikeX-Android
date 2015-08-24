package com.unb.bikex.wireless;

import android.bluetooth.BluetoothSocket;

import com.unb.bikex.threadutils.ICallbackThread;

import java.io.IOException;

/**
 * Created by Charles on 8/2/2015.
 */
public interface IBluetoothConnection {

    /**
     * Listener para threads
     */
    void setListener(ICallbackThread iListener);

    /**
     * Retorna uma string que pode ser usada numa Intent para pedir permiss�o para ligar o Bluetoooth, caso este esteja desligado.
     * @param
     * @return String
     */
    public String enable();


    /**
     * Abre uma nova thread para conex�o com o dispositivo especificado pelo endere�o MAC passado no construtor
     */
    void connectToDevice(String address);

    /**
     * Fecha conex�o com o dispositivo
     */
    void disconnectToDevice() throws IOException;

    /**
     * Verifica se existe conex�o com algum dispositivo
     */
    boolean isConnected();

    /**
     * Retorna BluetoothSocket que dever� ser usado para comunica��o
     */
    BluetoothSocket getBluetoothSocket();

    /**
     * Retorna nome do dispositivo ao qual foi estabelecida uma conex�o
     */
    String getDeviceName();
}
