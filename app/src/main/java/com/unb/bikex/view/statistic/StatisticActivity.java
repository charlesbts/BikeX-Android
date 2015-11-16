package com.unb.bikex.view.statistic;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.unb.bikex.R;
import com.unb.bikex.adapter.StatisticAdapter;
import com.unb.bikex.entity.Statistic;
import com.unb.bikex.presenter.StatisticPresenter;
import com.unb.bikex.view.BaseActivity;
import com.unb.bikex.view.main.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Charles on 11/16/2015.
 */
public class StatisticActivity extends BaseActivity implements IStatisticView, AdapterView.OnItemClickListener {
    @Inject StatisticPresenter statisticPresenter;
    private StatisticAdapter statisticAdapter;
    private ListView statisticListView;
    private long trackCod;
    private String trackName;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            trackCod = extras.getLong(MainActivity.COD_TRACK);
            trackName = extras.getString(MainActivity.NAME_TRACK);
        }
        setTitle(trackName);
        statisticListView = (ListView) findViewById(R.id.statistic_list);
        statisticListView.setOnItemClickListener(this);

    }

    @Override
    public void onResume(){
        super.onResume();
        statisticPresenter.onResume(trackCod);
    }

    @Override
    public void setItemsStatisticListView(List<Statistic> statisticList){
        statisticAdapter = new StatisticAdapter(this, statisticList);
        statisticListView.setAdapter(statisticAdapter);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new StatisticModule(this));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Statistic statistic = (Statistic) statisticAdapter.getItem(position);
        String formattedMessage = "Average Cadence: " + statistic.getAverageCadence() + "\n" +
                "Average Speed: " + statistic.getAverageSpeed() + "\n" +
                "Elapsed Time: " + statistic.getElapsedTime() + "\n" +
                "Distance: " + statistic.getDistance();

        AlertDialog alert = new AlertDialog.Builder(this)
                .setTitle(statistic.getTimeStamp())
                .setMessage(formattedMessage)
                .setPositiveButton("Ok", null)
                .create();
        alert.show();
    }
}
