package com.unb.bikex;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.unb.bikex.app.BikeXApp;

import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by Charles on 8/6/2015.
 */
public abstract class BaseActivity extends ActionBarActivity{
    protected ObjectGraph activityGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGraph = ((BikeXApp) getApplication()).createScopedGraph(getModules().toArray());
        activityGraph.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityGraph = null;
    }

    protected abstract List<Object> getModules();
}
