package com.unb.bikex.view.track;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.unb.bikex.view.BaseActivity;
import com.unb.bikex.R;
import com.unb.bikex.adapter.FragmentAdapter;
import com.unb.bikex.view.main.MainActivity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Charles on 9/22/2015.
 */
public class TrackActivity extends BaseActivity implements MapEvents {
    TrackModule trackModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    protected List<Object> getModules() {
        trackModule = new TrackModule(null);
        return Arrays.<Object>asList(trackModule);
    }

    @Override
    public void onFirstMarkerAchieve(){
        SensorFragment sensorFragment = (SensorFragment) getSupportFragmentManager().getFragments().get(0);
        sensorFragment.onStartTrack();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("TrackActivity", "onDestroy");
    }
}
