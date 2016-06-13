package com.rishabhjain.jvocsportscorner;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rishabhjain.jvocsportscorner.MatchSingleSE_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.MatchSingleSE_AdVh.ItemModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchSingleGameFragment extends Fragment {

    private static Context instance;
    private List<ItemModel> models;
    private String[] sub_event_names, sub_event_participants;

    public MatchSingleGameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches_single_game, container, false);
        instance = this.getContext();
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

    public static Context getInstance() {
        return instance;
    }
}
