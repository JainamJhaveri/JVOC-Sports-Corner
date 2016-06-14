package com.rishabhjain.jvocsportscorner.Matches;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.RoundSchedule_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.RoundSchedule_AdVh.ItemModel;
import com.rishabhjain.jvocsportscorner.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoundScheduleFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private List<ItemModel> models = null;
    private String[] game_nos, p1_names, p2_names, p1_mem_nos, p2_mem_nos;
    Spinner SESpinner;
    RecyclerView recyclerView;

    public RoundScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();
    }

    private void initializeList() {
        models = new ArrayList<>();
        game_nos = getContext().getResources().getStringArray(R.array.gameNos);
        p1_names = getContext().getResources().getStringArray(R.array.p1Names);
        p2_names = getContext().getResources().getStringArray(R.array.p2Names);
        p1_mem_nos = getContext().getResources().getStringArray(R.array.p1MemNos);
        p2_mem_nos = getContext().getResources().getStringArray(R.array.p2MemNos);

        for( int i=0; i<p1_names.length; i++ ){
            models.add(new ItemModel(game_nos[i], p1_names[i], p2_names[i], p1_mem_nos[i], p2_mem_nos[i]));
        }
    }
    private void setUpSpinner() {
        List<String> se_list = new ArrayList<>();
        String[] se_array = getResources().getStringArray(R.array.sub_event_names);
        Collections.addAll(se_list, se_array);
        ArrayAdapter<String> se_adapter = new ArrayAdapter<>
                (this.getContext(), android.R.layout.simple_spinner_item, se_list);

        se_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SESpinner.setAdapter(se_adapter);
        SESpinner.setOnItemSelectedListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_round_schedule, container, false);
        getReferences(view);
        setUpSpinner();
        ContentAdapter adapter = new ContentAdapter(models);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private void getReferences(View view) {
        SESpinner = (Spinner) view.findViewById(R.id.SESpinner);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
