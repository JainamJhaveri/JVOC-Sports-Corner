package com.rishabhjain.jvocsportscorner.SEParticipants_AdVh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<ItemModel> models;

    public ContentAdapter(List<ItemModel> models) {
        this.models = new ArrayList<>(models);
    }

    public void animateTo(List<ItemModel> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }



    private void applyAndAnimateRemovals(List<ItemModel> newModels) {
        for (int i = models.size() - 1; i >= 0; i--) {
            final ItemModel model = models.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<ItemModel> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final ItemModel model = newModels.get(i);
            if (!models.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<ItemModel> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final ItemModel model = newModels.get(toPosition);
            final int fromPosition = models.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public ItemModel removeItem(int position) {
        final ItemModel model = models.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, ItemModel model) {
        models.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final ItemModel model = models.remove(fromPosition);
        models.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder myholder = (ViewHolder) holder;
        final ItemModel itemModel = models.get(position);
        myholder.bind(itemModel);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
