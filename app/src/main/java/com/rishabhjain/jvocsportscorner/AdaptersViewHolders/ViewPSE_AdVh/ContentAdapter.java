package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.ViewPSE_AdVh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jainu on 10/6/16.
 */
public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final int LENGTH = 8;
    private final List<ItemModel> models;

    public ContentAdapter(List<ItemModel> models) {
        this.models = new ArrayList<>(models);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ItemModel itemModel = models.get(position % 2);
        holder.bind(itemModel);
    }

    @Override
    public int getItemCount() {
        return LENGTH;
    }
}