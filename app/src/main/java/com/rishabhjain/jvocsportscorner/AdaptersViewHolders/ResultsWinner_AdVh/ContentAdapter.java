package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.ResultsWinner_AdVh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final int LENGTH = 4;
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
        final ItemModel model = models.get(position % 2);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return LENGTH;
    }
}
