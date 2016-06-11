package com.rishabhjain.jvocsportscorner;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rishabhjain.jvocsportscorner.Events_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.Events_AdVh.ItemModel;

import java.util.ArrayList;
import java.util.List;

import static com.rishabhjain.jvocsportscorner.General.Constants.ADD_EVENT_REQ_CODE;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_ADDEVENTTITLE;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_ENDTIME;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_EVENTDATE;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_EVENTNAME;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_EVENTVENUE;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_STARTTIME;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_UNIQUEPARTICIPANTS;
import static com.rishabhjain.jvocsportscorner.General.Constants.EVENT_ADDED;
import static com.rishabhjain.jvocsportscorner.General.Constants.EVENT_NOT_ADDED;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_ADDEVENTTITILE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_ENDTIME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTDATE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTNAME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTVENUE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_STARTTIME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_UNIQUEPARTICIPANTS;

public class EventsFragment extends Fragment {

    RecyclerView recyclerView;
    ContentAdapter adapter;
    private List<ItemModel> models = null;
    private String[] eventnames, venues, dates, times, participants;

    public EventsFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        if( id == R.id.add_new_event_menu){
            Intent i = new Intent(this.getActivity(), AddEvent.class);
            i.putExtra(TAG_EVENTNAME, DEFAULT_EVENTNAME);
            i.putExtra(TAG_EVENTVENUE, DEFAULT_EVENTVENUE);
            i.putExtra(TAG_EVENTDATE, DEFAULT_EVENTDATE);
            i.putExtra(TAG_STARTTIME, DEFAULT_STARTTIME);
            i.putExtra(TAG_ENDTIME, DEFAULT_ENDTIME);
            i.putExtra(TAG_UNIQUEPARTICIPANTS, DEFAULT_UNIQUEPARTICIPANTS);
            i.putExtra(TAG_ADDEVENTTITILE, DEFAULT_ADDEVENTTITLE);
            startActivityForResult(i, ADD_EVENT_REQ_CODE);
            return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( resultCode == EVENT_ADDED){
            if( requestCode == ADD_EVENT_REQ_CODE){
                // recyclerview should be updated here
            }
        }else if (resultCode == EVENT_NOT_ADDED){
            // no new event is added,hence the recyclerview should not be updated
        }

    }

    private void initializeList() {
        models = new ArrayList<>();
        eventnames = getContext().getResources().getStringArray(R.array.eventNames);
        venues = getContext().getResources().getStringArray(R.array.eventVenues);
        dates = getContext().getResources().getStringArray(R.array.eventDates);
        times = getContext().getResources().getStringArray(R.array.eventTimes);
        participants = getContext().getResources().getStringArray(R.array.eventTotalParticipants);

        for( int i=0; i<eventnames.length; i++ ){
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
}