package com.rishabhjain.jvocsportscorner.Events_AdVh;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rishabhjain.jvocsportscorner.R;

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
        boolean landscape = parent.getResources().getBoolean(R.bool.isLandscape);
        if(landscape){
            Log.e(TAG, "onCreateViewHolder: landscape" );
        }
        else{
            Log.e(TAG, "onCreateViewHolder: portrait" );
            parent.invalidate();
            parent.postInvalidate();
        }
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
