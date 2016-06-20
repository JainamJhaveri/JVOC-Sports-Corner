package com.rishabhjain.jvocsportscorner.Events;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.Events_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.Events_AdVh.ItemModel;
import com.rishabhjain.jvocsportscorner.R;

import java.util.ArrayList;
import java.util.List;

import static com.rishabhjain.jvocsportscorner.General.Constants.ADD_EVENT_REQ_CODE;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_ADDEVENTTITLE;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_ENDTIME;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_EVENTDATE;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_EVENTNAME;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_EVENTVENUE;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_STARTTIME;
import static com.rishabhjain.jvocsportscorner.General.Constants.EDIT_EVENT_REQ_CODE;
import static com.rishabhjain.jvocsportscorner.General.Constants.EVENT_ADDED;
import static com.rishabhjain.jvocsportscorner.General.Constants.EVENT_EDITED;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_ADDEVENTTITILE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EDITFLAG;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EDIT_POSITION;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_ENDTIME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTDATE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTNAME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTVENUE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_STARTTIME;

public class EventsFragment extends Fragment {

    private static EventsFragment event_fragment_instance;
    private final String TAG = this.getClass().getSimpleName();
    RecyclerView recyclerView;
    ContentAdapter adapter;
    private List<ItemModel> models = null;
    private String[] eventnames, venues, dates, times, participants;

    public EventsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        event_fragment_instance = this;
        initializeList();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_event, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_new_event_menu) {
            Intent i = new Intent(this.getActivity(), AddEvent.class);
            i.putExtra(TAG_EVENTNAME, DEFAULT_EVENTNAME);
            i.putExtra(TAG_EVENTVENUE, DEFAULT_EVENTVENUE);
            i.putExtra(TAG_EVENTDATE, DEFAULT_EVENTDATE);
            i.putExtra(TAG_STARTTIME, DEFAULT_STARTTIME);
            i.putExtra(TAG_ENDTIME, DEFAULT_ENDTIME);
            i.putExtra(TAG_ADDEVENTTITILE, DEFAULT_ADDEVENTTITLE);
            i.putExtra(TAG_EDITFLAG, false);    // add mode on
            startActivityForResult(i, ADD_EVENT_REQ_CODE);
            return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_EVENT_REQ_CODE || requestCode == EDIT_EVENT_REQ_CODE) {
            Bundle extras = data.getExtras();
            String eventname = extras.getString(TAG_EVENTNAME);
            String venue = extras.getString(TAG_EVENTVENUE);
            String date = extras.getString(TAG_EVENTDATE);
            String starttime = extras.getString(TAG_STARTTIME);
            String endtime = extras.getString(TAG_ENDTIME);

            if (resultCode == EVENT_ADDED) {
                Log.e(TAG, "onActivityResult: event added" );
                addtoRecyclerView(eventname, venue, date, starttime, endtime);
            } else if (resultCode == EVENT_EDITED) {
                int edit_position = extras.getInt(TAG_EDIT_POSITION);
                Log.e(TAG, "onActivityResult: request code: " + EDIT_EVENT_REQ_CODE + ", edit position: " + edit_position);
                ItemModel itemModel = new ItemModel(eventname, venue, date, starttime + " to " + endtime, "0");
                ContentAdapter.replaceItem(edit_position, itemModel);
                adapter.notifyDataSetChanged();
            }
        }

    }

    private void addtoRecyclerView(String eventname, String venue, String date, String starttime, String endtime) {
        ItemModel itemModel = new ItemModel(eventname, venue, date, starttime + " to " + endtime, "0");
        ContentAdapter.addItem(itemModel);
        adapter.notifyDataSetChanged();
    }

    private void initializeList() {
        models = new ArrayList<>();
        eventnames = getContext().getResources().getStringArray(R.array.eventNames);
        venues = getContext().getResources().getStringArray(R.array.eventVenues);
        dates = getContext().getResources().getStringArray(R.array.eventDates);
        times = getContext().getResources().getStringArray(R.array.eventTimes);
        participants = getContext().getResources().getStringArray(R.array.eventTotalParticipants);

        for (int i = 0; i < eventnames.length; i++) {
            models.add(new ItemModel(eventnames[i], venues[i], dates[i], times[i], participants[i]));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        adapter = new ContentAdapter(models);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public static Context getEventsInstance() {
        return event_fragment_instance.getActivity();
    }
}