package com.unb.bikex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.unb.bikex.R;
import com.unb.bikex.entity.Statistic;

import java.util.List;

/**
 * Created by Charles on 11/16/2015.
 */
public class StatisticAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Statistic> statisticList;

    public StatisticAdapter(Context context, List<Statistic> statisticList){
        this.layoutInflater = LayoutInflater.from(context);
        this.statisticList = statisticList;
    }

    @Override
    public int getCount(){
        return statisticList.size();
    }

    @Override
    public Object getItem(int position){
        return statisticList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(view == null){
            view = layoutInflater.inflate(R.layout.statistic_list_view, null);
            viewHolder = new ViewHolder();
            viewHolder.statisticTextView = (TextView) view.findViewById(R.id.statistic_time_stamp);
            viewHolder.calcImageView = (ImageView) view.findViewById(R.id.statistic_image_calc);
            viewHolder.arrowImageView = (ImageView) view.findViewById(R.id.statistic_image_arrow);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        final String statisticTimeStamp = statisticList.get(position).getTimeStamp();
        viewHolder.statisticTextView.setText(statisticTimeStamp);
        viewHolder.calcImageView.setImageResource(R.mipmap.calc);
        viewHolder.arrowImageView.setImageResource(R.mipmap.arrow);

        return view;

    }

    static class ViewHolder{
        TextView statisticTextView;
        ImageView calcImageView;
        ImageView arrowImageView;
    }
}
