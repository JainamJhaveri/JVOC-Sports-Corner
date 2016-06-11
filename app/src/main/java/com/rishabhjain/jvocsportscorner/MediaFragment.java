package com.rishabhjain.jvocsportscorner;


import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rishabhjain.jvocsportscorner.Media_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.Media_AdVh.ItemModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MediaFragment extends Fragment {

    RecyclerView recyclerView;
    ContentAdapter adapter;
    private List<ItemModel> models = null;
    private String[] names, dates;
    private Drawable[] coverImages;

    public MediaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();
    }

    private void initializeList() {
        models = new ArrayList<>();

        names = getContext().getResources().getStringArray(R.array.eventNames);
        dates = getContext().getResources().getStringArray(R.array.eventDates);

        TypedArray a = getContext().getResources().obtainTypedArray(R.array.eventCoverImages);
        coverImages = new Drawable[a.length()];
        for (int i = 0; i < coverImages.length; i++) {
            coverImages[i] = a.getDrawable(i);
        }
        a.recycle();

        for( int i=0; i<coverImages.length; i++){
            models.add(new ItemModel(names[i%2], dates[i%2], coverImages[i]));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        adapter = new ContentAdapter(models);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

}
