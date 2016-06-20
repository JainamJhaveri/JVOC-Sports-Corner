package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.SubEvents_AdVh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static List<ItemModel> models;

    public ContentAdapter(List<ItemModel> models) {
        this.models = new ArrayList<>(models);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    public static void addItem(ItemModel model) {
        models.add(model);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemModel itemModel = models.get(position);
        holder.bind(itemModel);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static void deleteItem(int position) {
        models.remove(position);
    }

    public static void replaceItem(int edit_position, ItemModel itemModel) {
        models.set(edit_position, itemModel);
    }
}