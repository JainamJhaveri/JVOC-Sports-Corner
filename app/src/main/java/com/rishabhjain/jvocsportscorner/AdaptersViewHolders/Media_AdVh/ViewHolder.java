package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.Media_AdVh;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.Dashboard.MainActivity;
import com.rishabhjain.jvocsportscorner.Media.MediaUploadActivity;
import com.rishabhjain.jvocsportscorner.R;

import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTDATE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTNAME;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView card_event_name, card_date, selected_event_name, selected_card_date;
    private ImageView card_image;

    ViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super( inflater.inflate(R.layout.item_media, parent, false));
        card_event_name = (TextView) itemView.findViewById(R.id.card_event_name);
        card_date = (TextView) itemView.findViewById(R.id.card_date);
        card_image = (ImageView) itemView.findViewById(R.id.card_image);

        card_event_name.setOnClickListener(this);
        card_date.setOnClickListener(this);
        card_image.setOnClickListener(this);
    }

    void bind(ItemModel itemModel){
        card_event_name.setText(itemModel.getName());
        card_date.setText(itemModel.getDate());
        card_image.setImageDrawable(itemModel.getCover_image());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_image:
                getReferences((ViewGroup)v.getParent());
                break;
            case R.id.grey_bg_image:
                getReferences((ViewGroup)v.getParent());
                break;
            case R.id.card_event_name:
                getReferences((ViewGroup)v.getParent().getParent());
                break;
            case R.id.card_date:
                getReferences((ViewGroup)v.getParent().getParent());
                break;
        }
        startMediaActivity();
    }

    private void startMediaActivity() {
        Intent i = new Intent(MainActivity.getMainAcInstance(), MediaUploadActivity.class);
        i.putExtra(TAG_EVENTNAME, selected_event_name.getText().toString());
        i.putExtra(TAG_EVENTDATE, selected_card_date.getText().toString());
        MainActivity.getMainAcInstance().startActivity(i);
    }

    private void getReferences(ViewGroup parent) {
        selected_card_date = (TextView) parent.findViewById(R.id.card_date);
        selected_event_name= (TextView) parent.findViewById(R.id.card_event_name);
    }
}
