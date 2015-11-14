package com.unb.bikex.view.track;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import com.unb.bikex.view.BaseActivity;
import com.unb.bikex.R;
import com.unb.bikex.adapter.FragmentAdapter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Charles on 9/22/2015.
 */
public class TrackActivity extends BaseActivity implements MapEvents {
    TrackModule trackModule;
    SensorFragment sensorFragment;

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

        keepScreenOn();
    }


    @Override
    protected List<Object> getModules() {
        trackModule = new TrackModule(null);
        return Arrays.<Object>asList(trackModule);
    }

    private void keepScreenOn(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private void releaseScreenOn(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onFirstMarkerAchieve(){
        sensorFragment = (SensorFragment) getSupportFragmentManager().getFragments().get(0);
        sensorFragment.onMapStartTrack();
    }

    @Override
    public void onLastMarkerAchieve(){
        sensorFragment.onMapStopTrack();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        releaseScreenOn();
    }
}
