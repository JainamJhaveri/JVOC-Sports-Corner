package com.rishabhjain.jvocsportscorner.Matches;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.MatchSingleSE_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.MatchSingleSE_AdVh.ItemModel;
import com.rishabhjain.jvocsportscorner.R;

import java.util.ArrayList;
import java.util.List;

public class IndividualGameFragment extends Fragment {

    private List<ItemModel> models;
    private String[] sub_event_names, sub_event_participants;

    public IndividualGameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches_single_game, container, false);
        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view) {
        initializeList();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        ContentAdapter adapter = new ContentAdapter(models);
        assert recyclerView != null;
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    private void initializeList() {
        models = new ArrayList<>();
        sub_event_names = getResources().getStringArray(R.array.sub_event_names);
        sub_event_participants = getResources().getStringArray(R.array.sub_event_participants);
        for (int i = 0; i < sub_event_names.length; i++) {
            models.add(new ItemModel(sub_event_names[i], sub_event_participants[i]));
        }
    }
}
