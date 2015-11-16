package com.unb.bikex.model.statistic;

import com.unb.bikex.entity.Statistic;

import java.util.List;

/**
 * Created by Charles on 11/16/2015.
 */
public interface IStatisticModel {

    List<Statistic> getStatisticList(long codTrack);
}
