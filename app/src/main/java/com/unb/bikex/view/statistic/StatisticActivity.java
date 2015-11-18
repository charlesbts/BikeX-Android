package com.unb.bikex.view.statistic;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.unb.bikex.R;
import com.unb.bikex.adapter.StatisticAdapter;
import com.unb.bikex.adapter.StatisticExpandableAdapter;
import com.unb.bikex.entity.Statistic;
import com.unb.bikex.presenter.StatisticPresenter;
import com.unb.bikex.view.BaseActivity;
import com.unb.bikex.view.main.MainActivity;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Charles on 11/16/2015.
 */
public class StatisticActivity extends BaseActivity implements IStatisticView{
    @Inject StatisticPresenter statisticPresenter;
    private StatisticExpandableAdapter statisticExpandableAdapter;
    private ExpandableListView statisticExpandableListView;
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

        statisticExpandableListView = (ExpandableListView) findViewById(R.id.statistic_list);

        statisticPresenter.onResume(trackCod);
    }

    @Override
    public void setItemsStatisticListView(List<Statistic> statisticList){
        statisticExpandableAdapter = new StatisticExpandableAdapter(this, statisticList);
        statisticExpandableListView.setAdapter(statisticExpandableAdapter);

    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new StatisticModule(this));
    }

}
