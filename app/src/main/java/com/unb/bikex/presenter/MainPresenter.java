package com.unb.bikex.presenter;

import com.unb.bikex.model.main.IMainModel;
import com.unb.bikex.view.main.IMainView;

/**
 * Created by Charles on 8/4/2015.
 */
public class MainPresenter{

    private IMainView iMainView;
    private IMainModel iMainModel;

    public MainPresenter(IMainView iMainView, IMainModel iMainModel){
        this.iMainView = iMainView;
        this.iMainModel = iMainModel;
    }

    public void onResume(){
        iMainView.setItemsTrackListView(iMainModel.getTrackList());
    }

}
