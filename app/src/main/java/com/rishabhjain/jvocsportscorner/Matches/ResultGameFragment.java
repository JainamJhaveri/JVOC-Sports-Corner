package com.rishabhjain.jvocsportscorner.Matches;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.ResultsWinner_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.ResultsWinner_AdVh.ItemModel;
import com.rishabhjain.jvocsportscorner.R;

import java.util.ArrayList;
import java.util.List;

public class ResultGameFragment extends Fragment {

    private List<ItemModel> models = null;
    private String[] se_names, wNames, rNames, wMemNos, rMemNos;

    public ResultGameFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();
    }

    private void initializeList() {
        models = new ArrayList<>();
        se_names = getContext().getResources().getStringArray(R.array.sub_event_names);
        wNames = getContext().getResources().getStringArray(R.array.p1Names);
        rNames = getContext().getResources().getStringArray(R.array.p2Names);
        wMemNos = getContext().getResources().getStringArray(R.array.p1MemNos);
        rMemNos = getContext().getResources().getStringArray(R.array.p2MemNos);

        for (int i = 0; i < wNames.length; i++) {
            models.add(new ItemModel(se_names[i % 2], wNames[i], rNames[i], wMemNos[i], rMemNos[i]));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result_game, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        ContentAdapter adapter = new ContentAdapter(models);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

}
