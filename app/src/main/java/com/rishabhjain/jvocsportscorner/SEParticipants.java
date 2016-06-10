package com.rishabhjain.jvocsportscorner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.General.DividerItemDecoration;
import com.rishabhjain.jvocsportscorner.SEParticipants_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.SEParticipants_AdVh.ItemModel;

import java.util.ArrayList;
import java.util.List;

import static com.rishabhjain.jvocsportscorner.General.Constants.ADD_PARTICIPANTS_REQ_CODE;
import static com.rishabhjain.jvocsportscorner.General.Constants.PARTICIPANTS_ADDED;
import static com.rishabhjain.jvocsportscorner.General.Constants.PARTICIPANTS_NOT_ADDED;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_SUBEVENT_NAME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_SUBEVENT_PARTICIPANTS;

public class SEParticipants extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private String[] names, mem_nos, genders, bdates;
    private List<ItemModel> models = null;
    ContentAdapter adapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    TextView subevent_no_of_participants;
    private static SEParticipants activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se_participants);

        initializeList();
        getReferences();
        initializeToolbar();
        setFieldValues();
        activity = this;
        initializeRecyclerView();
    }

    private void setFieldValues() {
        Bundle extras = getIntent().getExtras();
        setTitle(extras.getString(TAG_SUBEVENT_NAME));
        subevent_no_of_participants.setText(extras.getString(TAG_SUBEVENT_PARTICIPANTS));

    }

    private void initializeToolbar() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getReferences() {
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        subevent_no_of_participants = (TextView) findViewById(R.id.subevent_no_of_participants);
    }

    private void initializeRecyclerView() {
        adapter = new ContentAdapter(models);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
    }

    private void initializeList() {
        models = new ArrayList<>();
        names = getResources().getStringArray(R.array.mem_names);
        mem_nos = getResources().getStringArray(R.array.membership_nos);
        genders = getResources().getStringArray(R.array.mem_genders);
        bdates = getResources().getStringArray(R.array.mem_bdates);

        for (int i = 0; i < names.length; i++) {
            models.add(new ItemModel(names[i], mem_nos[i], genders[i], bdates[i]));
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.add_se_participants_menu:
                Intent i = new Intent(this, AddParticipants.class);
                i.putExtra(TAG_SUBEVENT_NAME, getTitle());
                startActivityForResult(i, ADD_PARTICIPANTS_REQ_CODE);
                return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == PARTICIPANTS_ADDED) {
            if (requestCode == ADD_PARTICIPANTS_REQ_CODE) {
                // recyclerview should be updated here
            }
        } else if (resultCode == PARTICIPANTS_NOT_ADDED) {
            // called when back button is pressed from AddSubEvent class
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_subeventparticipants, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<ItemModel> filteredModelList = filter(models, query);
        adapter.animateTo(filteredModelList);
        recyclerView.scrollToPosition(0);
        return true;
    }

    private List<ItemModel> filter(List<ItemModel> models, String query) {
        query = query.toLowerCase();

        final List<ItemModel> filteredModelList = new ArrayList<>();
        for (ItemModel model : models) {
            final String name_text = model.getName().toLowerCase();
            final String bdate_text = model.getBdate().toLowerCase();
            final String gender_text = model.getGender().toLowerCase();
            final String memno_text = model.getMem_no().toLowerCase();
            if (name_text.contains(query) || bdate_text.contains(query)
                    || gender_text.contains(query) || memno_text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    public static Activity getSEParticipantsAcInstance() {
        return activity;
    }
}
