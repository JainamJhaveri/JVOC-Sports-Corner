package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.Events_AdVh;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.General.Constants;
import com.rishabhjain.jvocsportscorner.Events.AddEvent;
import com.rishabhjain.jvocsportscorner.Dashboard.MainActivity;
import com.rishabhjain.jvocsportscorner.R;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private TextView event_name, no_of_participants, venue, date, time;
    Button b;
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

        event_name.setOnLongClickListener(this);
        no_of_participants.setOnLongClickListener(this);
        venue.setOnLongClickListener(this);
        date.setOnLongClickListener(this);
        time.setOnLongClickListener(this);

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
        final ViewGroup parent;
        if(v.getId() == R.id.button_notify_all_participants){
            parent = (ViewGroup) v.getParent();
            clicked_event_name = (TextView) parent.findViewById(R.id.event_name);
            clicked_date = (TextView) parent.findViewById(R.id.card_date);

            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.getMainAcInstance());
            dialog.setTitle("Notify").setMessage("Surely notify all participants for "
                                                +clicked_event_name.getText().toString()+ " ?");
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
            startAddEventActivity();
        }
        // if clicked area is child of direct child of card_event_single_item
        else{
            v = (ViewGroup)v.getParent();
            getClickedReferences(v);
            startAddEventActivity();
        }
    }

    private void startAddEventActivity() {
        Intent i = new Intent(MainActivity.getMainAcInstance(), AddEvent.class);
        i.putExtra(Constants.TAG_EVENTNAME, clicked_event_name.getText().toString());
        i.putExtra(Constants.TAG_EVENTVENUE, clicked_venue.getText().toString());
        i.putExtra(Constants.TAG_EVENTDATE, clicked_date.getText().toString());
        String startTimeString = (clicked_time.getText().toString()).substring(0,8);
        String endTimeString = (clicked_time.getText().toString()).substring(12);
        i.putExtra(Constants.TAG_STARTTIME, startTimeString);
        i.putExtra(Constants.TAG_ENDTIME, endTimeString);
        i.putExtra(Constants.TAG_UNIQUEPARTICIPANTS, clicked_no_of_participants.getText().toString());
        i.putExtra(Constants.TAG_ADDEVENTTITILE, clicked_event_name.getText().toString());
        MainActivity.getMainAcInstance().startActivityForResult(i, Constants.ADD_EVENT_REQ_CODE);
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
     This method is used for handling events to notify all participants for the selected event
     event_name and date are the primary key hence db can be queried to
     fetch venue and time and corresponding participants
      */
    private void notifyAllParticipants(String selected_event, String selected_date) {
        System.out.println(selected_event + " " + selected_date);
    }

    @Override
    public boolean onLongClick(View v) {
        if( v.getId() == R.id.button_notify_all_participants ) return true;

        if( isDirectChild(v) ){
            getClickedReferences(v);
        }else{
            v = (ViewGroup)v.getParent();
            getClickedReferences(v);
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.getMainAcInstance());
        dialog.setTitle("Delete Event").setMessage("Surely Delete the sub event " + clicked_event_name.getText().toString() + " ?");
        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                refreshRecyclerView();
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.create();
        dialog.show();

        return true;
    }

    private void refreshRecyclerView() {

    }
}
