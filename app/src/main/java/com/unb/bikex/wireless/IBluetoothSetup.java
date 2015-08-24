package com.unb.bikex.wireless;

import java.util.List;

/**
 * Created by Charles on 7/28/2015.
 */
public interface IBluetoothSetup {
    /**
     * Retorna uma string que pode ser usada numa Intent para pedir permissão para ligar o Bluetoooth, caso este esteja desligado.
     * @param
     * @return String
     */
    public String enable();

    /**
     * Retorna lista de dispositivos pareados com o celular.
     * @return List<String>
     */
    public List<String> getPairedDeviceList();




}
