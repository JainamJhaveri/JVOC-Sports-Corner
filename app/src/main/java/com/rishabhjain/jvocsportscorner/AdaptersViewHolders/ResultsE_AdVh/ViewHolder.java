package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.ResultsE_AdVh;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.Dashboard.MainActivity;
import com.rishabhjain.jvocsportscorner.General.Constants;
import com.rishabhjain.jvocsportscorner.R;
import com.rishabhjain.jvocsportscorner.ResultWinners;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView event_name, no_of_participants, venue, date, time;
    TextView clicked_event_name, clicked_no_of_participants, clicked_date;

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

    void bind(ItemModel itemModel) {
        event_name.setText(itemModel.getEventname());
        no_of_participants.setText(itemModel.getParticipants());
        venue.setText(itemModel.getVenue());
        date.setText(itemModel.getDate());
        time.setText(itemModel.getTime());
    }

    @Override
    public void onClick(View v) {

        if (isDirectChild(v)) {
            getClickedReferences(v);
            startViewPEventActivity();
        } else {
            v = (ViewGroup) v.getParent();
            getClickedReferences(v);
            startViewPEventActivity();
        }
    }

    private void startViewPEventActivity() {
        Intent i = new Intent(MainActivity.getMainAcInstance(), ResultWinners.class);
        i.putExtra(Constants.TAG_EVENTNAME, clicked_event_name.getText().toString());
        i.putExtra(Constants.TAG_EVENTDATE, clicked_date.getText().toString());
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
