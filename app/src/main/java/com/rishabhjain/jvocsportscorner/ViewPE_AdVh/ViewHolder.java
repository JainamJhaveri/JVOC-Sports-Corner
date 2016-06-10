package com.rishabhjain.jvocsportscorner.ViewPE_AdVh;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.MainActivity;
import com.rishabhjain.jvocsportscorner.R;
import com.rishabhjain.jvocsportscorner.ViewPSE;

import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTDATE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTNAME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_UNIQUEPARTICIPANTS;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView event_name, no_of_participants, venue, date, time;
    TextView clicked_event_name, clicked_no_of_participants, clicked_date;

    // inflate the item_event and not fragment_events because the textviews referenced are a part of item_event and not fragment_events
    ViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_event_view_p, parent, false));
        getReferences();
    }

    private void getReferences() {
        event_name = (TextView) itemView.findViewById(R.id.event_name);
        no_of_participants = (TextView) itemView.findViewById(R.id.no_of_participants);
        venue = (TextView) itemView.findViewById(R.id.card_venue);
        date = (TextView) itemView.findViewById(R.id.card_date);
        time = (TextView) itemView.findViewById(R.id.card_time);

        event_name.setOnClickListener(this);
        no_of_participants.setOnClickListener(this);
        venue.setOnClickListener(this);
        date.setOnClickListener(this);
        time.setOnClickListener(this);
    }

    void bind(ItemModel itemModel){
        event_name.setText(itemModel.getEventname());
        no_of_participants.setText(itemModel.getParticipants());
        venue.setText(itemModel.getVenue());
        date.setText(itemModel.getDate());
        time.setText(itemModel.getTime());
    }

    @Override
    public void onClick(View v) {

        // if area except notify button is clicked and if that clicked area is direct child of card_event_single_item
        if( isDirectChild(v) ){
            getClickedReferences(v);
            startViewPEventActivity();
        }
        // if clicked area is child of direct child of card_event_single_item
        else{
            v = (ViewGroup)v.getParent();
            getClickedReferences(v);
            startViewPEventActivity();
        }
    }

    private void startViewPEventActivity() {
        Intent i = new Intent(MainActivity.getMainAcInstance(), ViewPSE.class);
        i.putExtra(TAG_UNIQUEPARTICIPANTS, clicked_no_of_participants.getText().toString());
        i.putExtra(TAG_EVENTNAME, clicked_event_name.getText().toString());
        i.putExtra(TAG_EVENTDATE, clicked_date.getText().toString());
        MainActivity.getMainAcInstance().startActivity(i);
    }

    private boolean isDirectChild(View v) {
        ViewGroup parent = (ViewGroup) v.getParent();
        return parent.getId() == R.id.card_event_single_item;
    }

    private void getClickedReferences(View v) {
        ViewGroup parent = (ViewGroup) v.getParent();
        clicked_event_name = (TextView) parent.findViewById(R.id.event_name);
        clicked_no_of_participants = (TextView) parent.findViewById(R.id.no_of_participants);
        clicked_date = (TextView) parent.findViewById(R.id.card_date);
    }
}
