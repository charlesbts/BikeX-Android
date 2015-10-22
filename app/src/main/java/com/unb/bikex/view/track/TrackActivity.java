package com.unb.bikex.view.track;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.unb.bikex.view.BaseActivity;
import com.unb.bikex.R;
import com.unb.bikex.adapter.FragmentAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Charles on 9/22/2015.
 */
public class TrackActivity extends BaseActivity {
    TrackModule trackModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TrackActivity", "onCreate");
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
    public void onDestroy(){
        super.onDestroy();
        Log.d("TrackActivity", "onDestroy");
    }
}
