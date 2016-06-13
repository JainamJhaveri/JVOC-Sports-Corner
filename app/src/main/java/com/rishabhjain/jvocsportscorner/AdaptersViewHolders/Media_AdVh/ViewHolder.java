package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.Media_AdVh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView card_event_name, card_date;
    private ImageView card_image;

    ViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super( inflater.inflate(R.layout.item_media, parent, false));
        card_event_name = (TextView) itemView.findViewById(R.id.card_event_name);
        card_date = (TextView) itemView.findViewById(R.id.card_date);
        card_image = (ImageView) itemView.findViewById(R.id.card_image);
    }

    void bind(ItemModel itemModel){
        card_event_name.setText(itemModel.getName());
        card_date.setText(itemModel.getDate());
        card_image.setImageDrawable(itemModel.getCover_image());
    }

}
