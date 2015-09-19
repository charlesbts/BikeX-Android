package com.unb.bikex.adapter;

import android.content.Context;
import android.media.Image;
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
    int selectedPosition = 0;

    public BluetoothDeviceAdapter(Context context){
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setBluetoothDeviceList(List<String> bluetoothDeviceList){
        this.bluetoothDeviceList = bluetoothDeviceList;
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
    public View getView(int position, View view, ViewGroup viewGroup){
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
        String macAddress = deviceNameAndMacAddress.substring(macAddressIndex + 1);

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
