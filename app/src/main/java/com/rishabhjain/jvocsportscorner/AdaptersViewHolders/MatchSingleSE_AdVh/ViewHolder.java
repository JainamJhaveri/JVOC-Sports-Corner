package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.MatchSingleSE_AdVh;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.Matches.MScheduleActivity;
import com.rishabhjain.jvocsportscorner.R;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private final TextView sub_event_name;
    private final TextView no_of_participants;
    private TextView clicked_sub_event_name, clicked_no_of_participants;
    private Button b;

    ViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_single_match_se, parent, false));
        sub_event_name = (TextView) itemView.findViewById(R.id.sub_event_name);
        no_of_participants = (TextView) itemView.findViewById(R.id.no_of_participants);
        b = (Button) itemView.findViewById(R.id.button_schedule_se);

        sub_event_name.setOnClickListener(this);
        no_of_participants.setOnClickListener(this);
        b.setOnClickListener(this);
    }

    void bind(ItemModel itemModel) {
        sub_event_name.setText(itemModel.getSub_event_name());
        no_of_participants.setText(itemModel.getNo_of_participants());
    }

    private void getClickedReferences(View item) {
        ViewGroup parent = (ViewGroup) item.getParent();
        clicked_sub_event_name = (TextView) parent.findViewById(R.id.sub_event_name);
        clicked_no_of_participants = (TextView) parent.findViewById(R.id.no_of_participants);
        Log.e(TAG, "getClickedReferences: " +clicked_sub_event_name.getText().toString() + " " +clicked_no_of_participants.getText().toString() );
    }

    @Override
    public void onClick(View v) {
        final ViewGroup parent, superparent;
        if (v.getId() == R.id.button_schedule_se) {
            parent = (ViewGroup) v.getParent();
            superparent = (ViewGroup) parent.getParent();
            clicked_sub_event_name = (TextView) superparent.findViewById(R.id.sub_event_name);
            clicked_no_of_participants = (TextView) parent.findViewById(R.id.no_of_participants);

            AlertDialog.Builder dialog = new AlertDialog.Builder(MScheduleActivity.getMSAcInstance());

            System.out.println("dialog is null: " +dialog);
            dialog.setTitle("Schedule subevent")
                    .setMessage("Surely schedule " + clicked_sub_event_name.getText().toString() + " ?");
            dialog.setPositiveButton("Schedule", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    System.out.println(clicked_sub_event_name.getText().toString() + " " + clicked_no_of_participants.getText().toString());
//                    notifyAllParticipants(clicked_event_name.getText().toString(), clicked_date.getText().toString());
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
        } else if (isDirectChild(v)) {
            getClickedReferences(v);
            startRoundScheduleFragment();
        } else {
            v = (ViewGroup) v.getParent();
            getClickedReferences(v);
            startRoundScheduleFragment();
        }

    }

    private boolean isDirectChild(View v) {
        return v.getId() == R.id.card_event_single_item;
    }

    private void updateButton(ViewGroup parent) {
        Button clicked_button = (Button) parent.findViewById(R.id.button_schedule_se);
        parent.removeView(clicked_button);
        TextView scheduled_se_tv = (TextView) parent.findViewById(R.id.scheduled_se_tv);
        scheduled_se_tv.setVisibility(View.VISIBLE);
    }

    private void startRoundScheduleFragment() {

    }

}
