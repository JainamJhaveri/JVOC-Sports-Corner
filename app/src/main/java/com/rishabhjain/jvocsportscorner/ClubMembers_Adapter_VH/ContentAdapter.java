package com.rishabhjain.jvocsportscorner.ClubMembers_Adapter_VH;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rishabhjain.jvocsportscorner.R;

import java.util.ArrayList;
import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = this.getClass().getSimpleName();
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private final List<ItemModel> models;

    public ContentAdapter(List<ItemModel> models) {
        this.models = new ArrayList<>(models);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM)
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        else if (viewType == TYPE_FOOTER)
            return new FooterHolder(LayoutInflater.from(parent.getContext()), parent);
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {

            ViewHolder myholder = (ViewHolder) holder;
            final ItemModel itemModel = models.get(position);
            myholder.bind(itemModel);

        } else if (holder instanceof FooterHolder) {  // last_updated_on textview will be set from this block

            Log.e(TAG, "onBindViewHolder:instance of footer holder ");
            FooterHolder myHolder = (FooterHolder) holder;
            myHolder.tv_updated_on.setText("01-02-2003");

        }
    }

    @Override
    public int getItemViewType(int position) {
        Log.e(TAG, "getItemViewType: " + position);
        if (position == models.size())
            return TYPE_FOOTER;
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return models.size() + 1;
    }   // + 1 is for footer

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
    
    

}