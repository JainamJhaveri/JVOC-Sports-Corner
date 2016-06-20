package com.rishabhjain.jvocsportscorner.ViewParticipants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.SEParticipants_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.SEParticipants_AdVh.ItemModel;
import com.rishabhjain.jvocsportscorner.Events.AddParticipants;
import com.rishabhjain.jvocsportscorner.General.DividerItemDecoration;
import com.rishabhjain.jvocsportscorner.R;

import java.util.ArrayList;
import java.util.List;

import static com.rishabhjain.jvocsportscorner.General.Constants.ADD_PARTICIPANTS_REQ_CODE;
import static com.rishabhjain.jvocsportscorner.General.Constants.PARTICIPANTS_ADDED;
import static com.rishabhjain.jvocsportscorner.General.Constants.PARTICIPANTS_NOT_ADDED;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_ADDED_PARTICIPANTS_ARRAY;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_SE_EDIT_POSITION;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_SUBEVENT_NAME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_SUBEVENT_PARTICIPANTS;

public class SEParticipants extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private String[] names, mem_nos, genders, bdates;
    private List<ItemModel> models = null;
    static ContentAdapter adapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    static TextView subevent_no_of_participants;
    private static SEParticipants activity;
    private int editposition;

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
        editposition = extras.getInt(TAG_SE_EDIT_POSITION);
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
                handleBackPressed();
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
    public void onBackPressed() {
        handleBackPressed();
    }

    private void handleBackPressed() {
        Intent data = new Intent();
        data.putExtra(TAG_SUBEVENT_PARTICIPANTS, subevent_no_of_participants.getText().toString());
        data.putExtra(TAG_SUBEVENT_NAME, getTitle().toString());
        data.putExtra(TAG_SE_EDIT_POSITION, editposition);
        setResult(PARTICIPANTS_ADDED, data);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == PARTICIPANTS_ADDED) {
            if (requestCode == ADD_PARTICIPANTS_REQ_CODE) {
                System.out.println("participants added");
                Bundle extras = data.getExtras();
                ArrayList<Parcelable> addedPList = extras.getParcelableArrayList(TAG_ADDED_PARTICIPANTS_ARRAY);
                for(int i=0; i<addedPList.size(); i++){
                    System.out.println(addedPList.get(i));
                    com.rishabhjain.jvocsportscorner.AdaptersViewHolders.AddParticipants_AdVh.ItemModel item = (com.rishabhjain.jvocsportscorner.AdaptersViewHolders.AddParticipants_AdVh.ItemModel) addedPList.get(i);
                    addRVItem(new ItemModel(item.getName(), item.getMem_no(), item.getGender(), item.getBdate()));
                }
            }
        } else if (resultCode == PARTICIPANTS_NOT_ADDED) {

            System.out.println("participants not added");
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
        final List<ItemModel> filteredModelList = filter(query);
        adapter.animateTo(filteredModelList);
        recyclerView.scrollToPosition(0);
        return true;
    }

    private List<ItemModel> filter(String query) {
        query = query.toLowerCase();

        final List<ItemModel> filteredModelList = new ArrayList<>();
        for (ItemModel model : ContentAdapter.getModels()) {
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

    private void addRVItem(ItemModel itemModel) {       // TODO: AddParticipant.php will be called from here, check for duplication on server side before insertion
        ContentAdapter.addItem(itemModel);
        adapter.notifyDataSetChanged();
//        addParticipantCount();
        int se_p_count = Integer.parseInt(subevent_no_of_participants.getText().toString()) + 1;
        subevent_no_of_participants.setText(String.valueOf(se_p_count));
    }

    public static void deleteRVItemAt(int position) {                   // TODO: RemoveParticipant.php will be called from here
        ContentAdapter.deleteItem(position);
        adapter.notifyDataSetChanged();
//        reduceParticipantCount();
        int se_p_count = Integer.parseInt(subevent_no_of_participants.getText().toString()) - 1;
        subevent_no_of_participants.setText(String.valueOf(se_p_count));
    }
}
