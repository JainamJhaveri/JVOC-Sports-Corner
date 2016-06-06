package com.rishabhjain.jvocsportscorner;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rishabhjain.jvocsportscorner.Events_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.Events_AdVh.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class EventsFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();
    RecyclerView recyclerView;
    ContentAdapter adapter;
    private List<ItemModel> models = null;
    private String[] eventnames, venues, dates, times;
    private String[] participants;
    Button b;

    public EventsFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();
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
        View view = null;
        boolean landscape = getResources().getBoolean(R.bool.isLandscape);
        if(landscape){
            Log.e(TAG, "onCreateView: landscape" );
            if( view != null )
                view.invalidate();
        }
        else{
            Log.e(TAG, "onCreateView: portrait" );
            if( view != null )
                view.invalidate();
        }

        view = inflater.inflate(R.layout.fragment_events, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        adapter = new ContentAdapter(models);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}