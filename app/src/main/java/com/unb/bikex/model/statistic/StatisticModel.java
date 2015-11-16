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
        Statistic statistic2 = new Statistic(2, "13/03/13", 23, 23, 38, 10);
        Statistic statistic3 = new Statistic(3, "14/04/14", 233, 223, 342, 19);
        mockList = new ArrayList<>();
        mockList.add(statistic); mockList.add(statistic2); mockList.add(statistic3);
        return mockList;
    }
}
