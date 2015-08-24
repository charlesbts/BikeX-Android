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
     * Retorna uma string que pode ser usada numa Intent para pedir permissão para ligar o Bluetoooth, caso este esteja desligado.
     * @param
     * @return String
     */
    public String enable();


    /**
     * Abre uma nova thread para conexão com o dispositivo especificado pelo endereço MAC passado no construtor
     */
    void connectToDevice(String address);

    /**
     * Fecha conexão com o dispositivo
     */
    void disconnectToDevice() throws IOException;

    /**
     * Verifica se existe conexão com algum dispositivo
     */
    boolean isConnected();

    /**
     * Retorna BluetoothSocket que deverá ser usado para comunicação
     */
    BluetoothSocket getBluetoothSocket();

    /**
     * Retorna nome do dispositivo ao qual foi estabelecida uma conexão
     */
    String getDeviceName();
}
