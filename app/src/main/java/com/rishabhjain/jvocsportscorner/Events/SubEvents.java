package com.rishabhjain.jvocsportscorner.Events;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.SubEvents_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.SubEvents_AdVh.ItemModel;
import com.rishabhjain.jvocsportscorner.General.Constants;
import com.rishabhjain.jvocsportscorner.R;

import java.util.ArrayList;
import java.util.List;

import static com.rishabhjain.jvocsportscorner.General.Constants.ADD_SUB_EVENT_REQ_CODE;
import static com.rishabhjain.jvocsportscorner.General.Constants.SUB_EVENT_ADDED;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_AGE_GROUP;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_GAME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_GENDER;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_TEAM_SIZE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_UNIQUEPARTICIPANTS;

public class SubEvents extends AppCompatActivity {
    TextView noOfParticipantsTV;
    private List<ItemModel> models;
    RecyclerView recyclerView;
    static ContentAdapter adapter;
    private static SubEvents activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_events);

        setupActionBar();
        getReferences();
        setFieldValues();
        setupRecyclerView();
        activity = this;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_new_sub_event_menu:
                Intent intent = new Intent(this, AddSubEvent.class);
                startActivityForResult(intent, ADD_SUB_EVENT_REQ_CODE);
                return true;
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getReferences() {
        noOfParticipantsTV = (TextView) findViewById(R.id.tv_total_no_of_participants);
    }

    private void setFieldValues() {
        Bundle extras = getIntent().getExtras();
        setTitle(extras.getString(Constants.TAG_EVENTNAME));
        noOfParticipantsTV.setText(extras.getString(TAG_UNIQUEPARTICIPANTS));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_subevent, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_SUB_EVENT_REQ_CODE && resultCode == SUB_EVENT_ADDED) {// update recyclerview as new subevents are added from AddSubEvent activity
            Bundle extras = data.getExtras();
            String game = extras.getString(TAG_GAME);
            String teamSize = extras.getString(TAG_TEAM_SIZE);
            String ageGroup = extras.getString(TAG_AGE_GROUP);
            String gameGender = extras.getString(TAG_GENDER);

            assert ageGroup != null;
            String[] ageGroupArray = ageGroup.split(",");
            assert gameGender != null;
            String[] gameGenderArray = gameGender.split(",");

            String gameGenderDispString = "", teamSizeDispString = "";
            for (String ageGroupItem : ageGroupArray) {
                for (String gameGenderItem : gameGenderArray) {
                    switch (gameGenderItem) {
                        case "M":
                            gameGenderDispString = "Males";
                            break;
                        case "F":
                            gameGenderDispString = "Females";
                            break;
                        default:
                            gameGenderDispString = "Open";
                            break;
                    }

                    assert game != null;
                    if (!game.equals("Chess")) {     // because there is no singles or doubles for chess
                        assert teamSize != null;
                        switch (teamSize) {
                            case "1":
                                teamSizeDispString = "Singles";
                                break;
                            case "2":
                                teamSizeDispString = "Doubles";
                                break;
                            default:
                                teamSizeDispString = "";
                                break;
                        }
                    }
                    String se_name = game + " " + teamSizeDispString + " " + ageGroupItem + " " + gameGenderDispString;

                    // TODO: check for duplication of subevents in the rv before adding
                    addtoRecyclerView(se_name);
                }
            }
        }
    }

    private void addtoRecyclerView(String se_name) {
        ItemModel newitem = new ItemModel(se_name, "0");
        ContentAdapter.addItem(newitem);
        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView() {
        initializeList();
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        adapter = new ContentAdapter(models);
        assert recyclerView != null;
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initializeList() {
        models = new ArrayList<>();
        String[] sub_event_names = getResources().getStringArray(R.array.sub_event_names);
        String[] sub_event_participants = getResources().getStringArray(R.array.sub_event_participants);
        for (int i = 0; i < sub_event_names.length; i++) {
            models.add(new ItemModel(sub_event_names[i], sub_event_participants[i]));
        }
    }

    public static SubEvents getSubEventsAcInstance() {
        return activity;
    }

    public static void deleteRVItemAt(int position) {
        ContentAdapter.deleteItem(position);
        adapter.notifyDataSetChanged();
    }
}
