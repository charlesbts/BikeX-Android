package com.unb.bikex.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.unb.bikex.PageFragment2;
import com.unb.bikex.R;

/**
 * Created by Charles on 9/22/2015.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[];

    public FragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        tabTitles = new String[] {context.getString(R.string.tab_sensors), context.getString(R.string.tab_map)};
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("FragmentAdapter", "getItem");
        switch (position){
            case 0:
                return new SensorFragment();
            case 1:
                return new PageFragment2();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
