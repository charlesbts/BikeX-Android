package com.unb.bikex.presenter;

import com.unb.bikex.model.statistic.IStatisticModel;
import com.unb.bikex.view.statistic.IStatisticView;

/**
 * Created by Charles on 11/16/2015.
 */
public class StatisticPresenter {
    private IStatisticView iStatisticView;
    private IStatisticModel iStatisticModel;

    public StatisticPresenter(IStatisticView iStatisticView, IStatisticModel iStatisticModel){
        this.iStatisticView = iStatisticView;
        this.iStatisticModel = iStatisticModel;
    }

    public void onResume(long extras){
        iStatisticView.setItemsStatisticListView(iStatisticModel.getStatisticList(extras));
    }
}
