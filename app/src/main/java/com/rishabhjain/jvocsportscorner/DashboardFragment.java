package com.rishabhjain.jvocsportscorner;


import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rishabhjain.jvocsportscorner.Dashboard_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.Dashboard_AdVh.ItemModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    private String names[];
    private Drawable pictures[];
    private List<ItemModel> models;

    public DashboardFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();
    }

    private void initializeList() {
        models = new ArrayList<>();
        names = getContext().getResources().getStringArray(R.array.dashBoardNames);
        TypedArray a = getContext().getResources().obtainTypedArray(R.array.dashBoardImages);
        pictures = new Drawable[a.length()];
        for (int i = 0; i < pictures.length; i++) {
            pictures[i] = a.getDrawable(i);
        }
        a.recycle();

        for( int i=0; i<a.length(); i++){
            models.add(new ItemModel(names[i], pictures[i]));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recyclerview, container, false);
        ContentAdapter adapter = new ContentAdapter(models);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        int tilePadding = getResources().getDimensionPixelSize(R.dimen.port_tile_padding);
        int tiles_per_row;
        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            tiles_per_row = 2;
            tilePadding = getResources().getDimensionPixelSize(R.dimen.port_tile_padding);
        }
        else {
            tiles_per_row = 4;
            tilePadding = getResources().getDimensionPixelSize(R.dimen.land_tile_padding);
        }
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), tiles_per_row));
        return recyclerView;

    }

}
