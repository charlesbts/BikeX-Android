package com.unb.bikex.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.unb.bikex.BaseActivity;
import com.unb.bikex.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Charles on 9/22/2015.
 */
public class TrackActivity extends BaseActivity {
    MapTrackModule mapTrackModule;

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

        /* TODO: Apenas caso de teste view de preferencias */
        SharedPreferences teste = getSharedPreferences("userPreferences", AppCompatActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = teste.edit();
        editor.clear();
        editor.commit();

    }


    @Override
    protected List<Object> getModules() {
        mapTrackModule = new MapTrackModule(null);
        return Arrays.<Object>asList(mapTrackModule);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("TrackActivity", "onDestroy");
    }
}
