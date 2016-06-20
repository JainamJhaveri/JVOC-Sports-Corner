package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.MediaEIndividual_AdVh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rishabhjain.jvocsportscorner.Media.DetailImageAc;
import com.rishabhjain.jvocsportscorner.Media.MediaUploadActivity;
import com.rishabhjain.jvocsportscorner.R;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView imageView;
//    private int position;

    ViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super( inflater.inflate(R.layout.item_media_e_ind, parent, false));
        imageView = (ImageView) itemView.findViewById(R.id.item_img);
        imageView.setOnClickListener(this);
    }

    void bind(ItemModel model){
        // use model.getUrl in .load()
        Context context = MediaUploadActivity.getMUInstance();
        Glide.with(context)
                .load(R.drawable.events)
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    @Override
    public void onClick(View v) {
        Context context = MediaUploadActivity.getMUInstance();
        Intent i = new Intent(context, DetailImageAc.class);
        context.startActivity(i);
    }
}
