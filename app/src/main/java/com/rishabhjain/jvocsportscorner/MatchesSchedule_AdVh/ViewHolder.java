package com.rishabhjain.jvocsportscorner.MatchesSchedule_AdVh;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.MainActivity;
import com.rishabhjain.jvocsportscorner.MatchScheduleActivity;
import com.rishabhjain.jvocsportscorner.R;

import static com.rishabhjain.jvocsportscorner.General.Constants.ADD_EVENT_REQ_CODE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTDATE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTNAME;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView event_name, no_of_participants, venue, date, time;
    Button b;
    ViewGroup parent;
    TextView clicked_event_name, clicked_no_of_participants, clicked_venue, clicked_date, clicked_time;

    // inflate the item_event and not fragment_events because the textviews referenced are a part of item_event and not fragment_events
    ViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_event, parent, false));
        getReferences();
    }

    private void getReferences() {
        event_name = (TextView) itemView.findViewById(R.id.event_name);
        no_of_participants = (TextView) itemView.findViewById(R.id.no_of_participants);
        venue = (TextView) itemView.findViewById(R.id.card_venue);
        date = (TextView) itemView.findViewById(R.id.card_date);
        time = (TextView) itemView.findViewById(R.id.card_time);
        b = (Button) itemView.findViewById(R.id.button_notify_all_participants);

        b.setOnClickListener(this);

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
        // if notify all participants button is clicked
        if(v.getId() == R.id.button_notify_all_participants){
            parent = (ViewGroup) v.getParent();
            clicked_event_name = (TextView) parent.findViewById(R.id.event_name);
            clicked_date = (TextView) parent.findViewById(R.id.card_date);

            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.getMainAcInstance());
            dialog.setTitle("Notify").setMessage("Notify all participants for "
                                                +clicked_event_name.getText().toString()+ "'s schedule?");
            dialog.setPositiveButton("Notify all", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    System.out.println(clicked_event_name.getText().toString() + " " + clicked_date.getText().toString());
                    notifyAllParticipants(clicked_event_name.getText().toString(), clicked_date.getText().toString());
                    updateButton(parent);
                }
            });

            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.create();
            dialog.show();
        }
        // if area except notify button is clicked and if that clicked area is direct child of card_event_single_item
        else if( isDirectChild(v) ){
            getClickedReferences(v);
            startMatchScheduleActivity();
        }
        // if clicked area is child of direct child of card_event_single_item
        else{
            v = (ViewGroup)v.getParent();
            getClickedReferences(v);
            startMatchScheduleActivity();
        }
    }

    private void startMatchScheduleActivity() {
        Intent i = new Intent(MainActivity.getMainAcInstance(), MatchScheduleActivity.class);
        i.putExtra(TAG_EVENTNAME, clicked_event_name.getText().toString());
        i.putExtra(TAG_EVENTDATE, clicked_date.getText().toString());
        MainActivity.getMainAcInstance().startActivityForResult(i, ADD_EVENT_REQ_CODE);
    }

    private boolean isDirectChild(View v) {
        ViewGroup parent = (ViewGroup) v.getParent();
        return parent.getId() == R.id.card_event_single_item;
    }

    private void getClickedReferences(View v) {
        ViewGroup parent = (ViewGroup) v.getParent();
        clicked_event_name = (TextView) parent.findViewById(R.id.event_name);
        clicked_venue = (TextView) parent.findViewById(R.id.card_venue);
        clicked_date = (TextView) parent.findViewById(R.id.card_date);
        clicked_time = (TextView) parent.findViewById(R.id.card_time);
        clicked_no_of_participants = (TextView) parent.findViewById(R.id.no_of_participants);
    }

    private void updateButton(ViewGroup parent) {
        Button clicked_button = (Button) parent.findViewById(R.id.button_notify_all_participants);
        parent.removeView(clicked_button);
        TextView notified_tv = (TextView) parent.findViewById(R.id.notified_tv);
        notified_tv.setVisibility(View.VISIBLE);
    }

    /*
     This method is used for handling events to notify all participants for the selected event's schedule
     event_name and date are the primary key hence db can be queried to
     fetch venue and time and corresponding participants
      */
    private void notifyAllParticipants(String selected_event, String selected_date) {
        System.out.println(selected_event + " " + selected_date);
    }


    private void refreshRecyclerView() {

    }
}
