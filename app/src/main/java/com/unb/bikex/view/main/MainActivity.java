package com.unb.bikex.view.main;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.unb.bikex.model.main.Track;
import com.unb.bikex.view.BaseActivity;
import com.unb.bikex.R;
import com.unb.bikex.adapter.TrackAdapter;
import com.unb.bikex.presenter.MainPresenter;
import com.unb.bikex.view.track.TrackActivity;
import com.unb.bikex.view.userpreferences.UserPreferencesActivity;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;


public class MainActivity extends BaseActivity implements IMainView, AdapterView.OnItemClickListener {
    private ListView trackListView;
    @Inject MainPresenter mainPresenter;
    @Inject TrackAdapter trackAdapter;
    public final static String COD_TRACK = "cod_track";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trackListView = (ListView) findViewById(R.id.trackListView);
        trackListView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mainPresenter.onResume();
    }

    @Override
    public void setItemsTrackListView(List<Track> items) {
        trackAdapter.setTrackList(items);
        trackListView.setAdapter(trackAdapter);
    }

    public void invokeMapTrack(View view){
        Intent intent = new Intent(MainActivity.this, TrackActivity.class);
        startActivity(intent);
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new MainModule(this));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Track track = (Track) trackAdapter.getItem(position);
        Intent intent = new Intent(MainActivity.this, TrackActivity.class);
        intent.putExtra(COD_TRACK, track.getCod());
        startActivity(intent);
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

        if (id == R.id.action_user_preferences) {
            Intent intent = new Intent(MainActivity.this, UserPreferencesActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
