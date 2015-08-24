package com.unb.bikex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.unb.bikex.R;

import java.util.List;

/**
 * Created by Charles on 8/7/2015.
 */
public class TrackAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<String>  trackList;

    public TrackAdapter(Context context){
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setTrackList(List<String> trackList){
        this.trackList = trackList;
    }

    @Override
    public int getCount(){
        return this.trackList.size();
    }

    @Override
    public Object getItem(int position){
        return this.trackList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup){
        ViewHolder viewHolder;

        if(view == null){
            view = layoutInflater.inflate(R.layout.new_track_list_view, null);
            viewHolder = new ViewHolder();
            viewHolder.trackNameTextView = (TextView) view.findViewById(R.id.trackNameView);
            viewHolder.trackImageView = (ImageView) view.findViewById(R.id.trackImageView);
            viewHolder.arrowImageView = (ImageView) view.findViewById(R.id.arrowImageView);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        String trackName = trackList.get(position);
        viewHolder.trackNameTextView.setText(trackName);
        viewHolder.trackImageView.setImageResource(R.mipmap.track);
        viewHolder.arrowImageView.setImageResource(R.mipmap.arrow);
        return view;
    }

    static class ViewHolder{
        TextView trackNameTextView;
        ImageView trackImageView;
        ImageView arrowImageView;
    }

}
