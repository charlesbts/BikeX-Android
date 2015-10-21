package com.unb.bikex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.unb.bikex.R;

import java.util.List;

/**
 * Created by Charles on 9/17/2015.
 */
public class BluetoothDeviceAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<String> bluetoothDeviceList;
    int selectedPosition = -1;
    String bluetoothMacAddress = "";

    public BluetoothDeviceAdapter(Context context){
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setBluetoothDeviceList(List<String> bluetoothDeviceList){
        this.bluetoothDeviceList = bluetoothDeviceList;
    }

    public void setDefaultMacAddress(String macAddress){
        setSelectedPosition(macAddress);
    }

    private void setSelectedPosition(String macAddress){
        for(int i = 0; i < bluetoothDeviceList.size(); i++){
            String bluetoothDevice = bluetoothDeviceList.get(i);
            int macAddressIndex = bluetoothDevice.indexOf('\n');
            if(bluetoothDevice.substring(macAddressIndex + 1).equals(macAddress)){
                selectedPosition = i;
                bluetoothMacAddress = macAddress;
            }
        }
    }

    public String getBluetoothMacAddress(){
        return bluetoothMacAddress;
    }

    @Override
    public int getCount(){
        return this.bluetoothDeviceList.size();
    }

    @Override
    public Object getItem(int position){
        return this.bluetoothDeviceList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup){
        ViewHolder viewHolder;

        if(view == null){
            view = layoutInflater.inflate(R.layout.bluetooth_device_list_view, null);
            viewHolder = new ViewHolder();
            viewHolder.bluetoothDeviceRadioButton = (RadioButton) view.findViewById(R.id.bluetoothDeviceRadioButton);
            viewHolder.bluetoothDeviceImageView = (ImageView) view.findViewById(R.id.bluetoothDeviceImageView);
            viewHolder.bluetoothDeviceTextView = (TextView) view.findViewById(R.id.bluetoothDeviceTextView);
            viewHolder.bluetoothMacAddressTextView = (TextView) view.findViewById(R.id.bluetoothMacAddressTextView);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        String deviceNameAndMacAddress = bluetoothDeviceList.get(position);
        int macAddressIndex = deviceNameAndMacAddress.indexOf('\n');
        String deviceName = deviceNameAndMacAddress.substring(0, macAddressIndex);
        final String macAddress = deviceNameAndMacAddress.substring(macAddressIndex + 1);

        viewHolder.bluetoothDeviceImageView.setImageResource(R.mipmap.bluetooth);
        viewHolder.bluetoothDeviceTextView.setText(deviceName);
        viewHolder.bluetoothMacAddressTextView.setText(macAddress);

        viewHolder.bluetoothDeviceRadioButton.setChecked(position == selectedPosition);
        viewHolder.bluetoothDeviceRadioButton.setTag(position);
        viewHolder.bluetoothDeviceRadioButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectedPosition = (Integer) v.getTag();
                notifyDataSetChanged();
                bluetoothMacAddress = macAddress;
            }
        });

        return view;
    }

    static class ViewHolder{
        RadioButton bluetoothDeviceRadioButton;
        ImageView bluetoothDeviceImageView;
        TextView bluetoothDeviceTextView;
        TextView bluetoothMacAddressTextView;
    }
}
