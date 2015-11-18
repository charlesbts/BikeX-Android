package com.unb.bikex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.unb.bikex.R;
import com.unb.bikex.entity.Statistic;
import java.util.List;

/**
 * Created by Charles on 11/17/2015.
 */
public class StatisticExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Statistic> parentList;

    public StatisticExpandableAdapter(Context context, List<Statistic> parentList){
        this.context = context;
        this.parentList = parentList;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return parentList.get(groupPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderChild viewHolder;

        Statistic statistic = (Statistic) getChild(groupPosition, childPosition);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.statistic_list_view_child, null);

            viewHolder = new ViewHolderChild();
            viewHolder.averageCadence = (TextView) convertView.findViewById(R.id.average_cadence);
            viewHolder.averageSpeed = (TextView) convertView.findViewById(R.id.average_speed);
            viewHolder.elapsedTime = (TextView) convertView.findViewById(R.id.elapsed_time);
            viewHolder.distance = (TextView) convertView.findViewById(R.id.distance);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolderChild) convertView.getTag();
        }

        viewHolder.averageCadence.setText(Float.toString(statistic.getAverageCadence()));
        viewHolder.averageSpeed.setText(Float.toString(statistic.getAverageSpeed()));
        viewHolder.elapsedTime.setText(Integer.toString(statistic.getElapsedTime()));
        viewHolder.distance.setText(Float.toString(statistic.getDistance()));

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.parentList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ViewHolderParent viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.statistic_list_view, null);

            viewHolder = new ViewHolderParent();
            viewHolder.statisticTextView = (TextView) convertView.findViewById(R.id.statistic_time_stamp);
            viewHolder.calcImageView = (ImageView) convertView.findViewById(R.id.statistic_image_calc);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolderParent) convertView.getTag();
        }

        final String statisticTimeStamp = parentList.get(groupPosition).getTimeStamp();
        viewHolder.statisticTextView.setText(statisticTimeStamp);
        viewHolder.calcImageView.setImageResource(R.mipmap.calc);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    static class ViewHolderParent{
        TextView statisticTextView;
        ImageView calcImageView;
    }

    static class ViewHolderChild{
        TextView averageCadence;
        TextView averageSpeed;
        TextView elapsedTime;
        TextView distance;
    }

}
