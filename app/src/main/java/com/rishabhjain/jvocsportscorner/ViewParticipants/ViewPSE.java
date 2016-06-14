package com.rishabhjain.jvocsportscorner.ViewParticipants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.ViewPSE_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.ViewPSE_AdVh.ItemModel;
import com.rishabhjain.jvocsportscorner.R;

import java.util.ArrayList;
import java.util.List;

import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTDATE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTNAME;

public class ViewPSE extends AppCompatActivity {

    private static Activity activity;
    private List<ItemModel> models;
    private String[] sub_event_names, sub_event_participants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pse);

        initializeToolbar();
        setTitle(getIntent().getExtras().getString(TAG_EVENTNAME));
        setupActionBar();
        activity = this;
        setupRecyclerView();
    }

    private void initializeList() {
        models = new ArrayList<>();
        sub_event_names = getResources().getStringArray(R.array.sub_event_names);
        sub_event_participants = getResources().getStringArray(R.array.sub_event_participants);
        for (int i = 0; i < sub_event_names.length; i++) {
            models.add(new ItemModel(sub_event_names[i], sub_event_participants[i]));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_all_participants, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.view_all_participants_menu:
                Intent i = new Intent(this, ViewPEAllP.class);
                i.putExtra( TAG_EVENTNAME, getIntent().getExtras().getString(TAG_EVENTNAME) );
                i.putExtra( TAG_EVENTDATE, getIntent().getExtras().getString(TAG_EVENTDATE) );
                startActivity(i);
                return true;
        }
        return false;
    }

    private void setupRecyclerView() {
        initializeList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        ContentAdapter adapter = new ContentAdapter(models);
        assert recyclerView != null;
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupActionBar() {
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initializeToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public static Activity getViewPSEAcInstance() {
        return activity;
    }
}
