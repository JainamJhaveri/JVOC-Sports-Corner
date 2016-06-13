package com.rishabhjain.jvocsportscorner.Matches;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rishabhjain.jvocsportscorner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoundScheduleFragment extends Fragment {


    public RoundScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_round_schedule, container, false);
    }

}
