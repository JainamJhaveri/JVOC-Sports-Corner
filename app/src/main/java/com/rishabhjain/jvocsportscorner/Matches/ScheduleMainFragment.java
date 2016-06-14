package com.rishabhjain.jvocsportscorner.Matches;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.ScheduleE_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.ScheduleE_AdVh.ItemModel;
import com.rishabhjain.jvocsportscorner.R;

import java.util.ArrayList;
import java.util.List;

public class ScheduleMainFragment extends Fragment {
    RecyclerView recyclerView;
    ContentAdapter adapter;
    private List<ItemModel> models = null;
    private String[] eventnames, venues, dates, times, participants;


    public ScheduleMainFragment() {}

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
        View view = inflater.inflate(R.layout.fragment_matches_schedule, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        adapter = new ContentAdapter(models);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

}
