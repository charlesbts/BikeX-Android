package com.unb.bikex.view.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.unb.bikex.BaseActivity;
import com.unb.bikex.R;
import com.unb.bikex.adapter.TrackAdapter;
import com.unb.bikex.presenter.MainPresenter;
import com.unb.bikex.view.MapTrackActivity;
import com.unb.bikex.view.TrackActivity;


import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;


public class MainActivity extends BaseActivity implements IMainView {
    private ListView trackListView;
    @Inject MainPresenter mainPresenter;
    @Inject TrackAdapter trackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trackListView = (ListView) findViewById(R.id.trackListView);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mainPresenter.onResume();
    }

    @Override
    public void setItemsTrackListView(List<String> items) {
        trackAdapter.setTrackList(items);
        trackListView.setAdapter(trackAdapter);
    }

    public void invokeMapTrack(View view){
        //Intent intent = new Intent(MainActivity.this, MapTrackActivity.class);
        Intent intent = new Intent(MainActivity.this, TrackActivity.class);
        startActivity(intent);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new MainModule(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
