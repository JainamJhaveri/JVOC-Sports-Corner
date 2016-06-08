package com.rishabhjain.jvocsportscorner.Events_AdVh;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.MainActivity;
import com.rishabhjain.jvocsportscorner.R;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private final String TAG = this.getClass().getSimpleName();
    private TextView event_name, no_of_participants, venue, date, time;
    Button b;
    ViewGroup parent;
    TextView clicked_event_name, clicked_date;

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
        b = (Button) itemView.findViewById(R.id.action_button);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: "+getAdapterPosition() );
            }
        });
        b.setOnClickListener(this);

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
        if(v.getId() == R.id.action_button){
            parent = (ViewGroup) v.getParent();
            clicked_event_name = (TextView) parent.findViewById(R.id.event_name);
            clicked_date = (TextView) parent.findViewById(R.id.card_date);

            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.getMainAcInstance());
            dialog.setTitle("Notify").setMessage("Surely notify all participants ? ");
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
    }

    private void updateButton(ViewGroup parent) {
        Button clicked_button = (Button) parent.findViewById(R.id.action_button);
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
}
