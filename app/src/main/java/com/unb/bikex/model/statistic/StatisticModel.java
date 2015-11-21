package com.unb.bikex.model.statistic;

import com.unb.bikex.database.DatabaseHelper;
import com.unb.bikex.entity.Statistic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charles on 11/16/2015.
 */
public class StatisticModel implements IStatisticModel {
    private DatabaseHelper databaseHelper;

    public StatisticModel(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    @Override
    public List<Statistic> getStatisticList(long codTrack){
        List<Statistic> statisticList;
        statisticList = databaseHelper.selectAllStatisticFromTrack(codTrack);

        return statisticList;
    }
}
