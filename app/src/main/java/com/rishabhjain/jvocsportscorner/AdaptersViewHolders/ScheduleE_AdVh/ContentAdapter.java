package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.ScheduleE_AdVh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ViewHolder>{
    private static final int LENGTH = 5;
    private final String TAG = this.getClass().getSimpleName();
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
        ItemModel itemModel = models.get(position % 2 );
        holder.bind(itemModel);
    }

    @Override
    public int getItemCount() {
        return LENGTH;
    }
}
