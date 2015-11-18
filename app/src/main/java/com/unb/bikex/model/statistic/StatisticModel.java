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
        List<Statistic> mockList;
        Statistic statistic = new Statistic(1, "12/02/12", 26, 33, 34, 111);
        databaseHelper.insertStatistic(codTrack, statistic);
        Statistic statistic2 = new Statistic(2, "13/03/13", 23, 23, 38, 10);
        databaseHelper.insertStatistic(codTrack, statistic2);
        mockList = databaseHelper.selectAllStatisticFromTrack(codTrack);

        return mockList;
    }
}
