package com.rishabhjain.jvocsportscorner.AddEvent_AdVh;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.AddEvent;
import com.rishabhjain.jvocsportscorner.R;
import com.rishabhjain.jvocsportscorner.SEParticipants;

import static com.rishabhjain.jvocsportscorner.General.Constants.*;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private final TextView sub_event_name;
    private final TextView no_of_participants;
    private TextView clicked_sub_event_name, clicked_no_of_participants;

    ViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_single_sub_event, parent, false));
        sub_event_name = (TextView) itemView.findViewById(R.id.sub_event_name);
        no_of_participants = (TextView) itemView.findViewById(R.id.no_of_participants);

        sub_event_name.setOnClickListener(this);
        no_of_participants.setOnClickListener(this);

        sub_event_name.setOnLongClickListener(this);
        no_of_participants.setOnLongClickListener(this);
    }

    void bind(ItemModel itemModel) {
        sub_event_name.setText(itemModel.getSub_event_name());
        no_of_participants.setText(itemModel.getNo_of_participants());
    }

    @Override
    public boolean onLongClick(View item) {
        getClickedReferences(item);
        AlertDialog.Builder dialog = new AlertDialog.Builder(AddEvent.getAddEventAcInstance());
        dialog.setTitle("Delete subevent").setMessage("Surely Delete the sub event " + clicked_sub_event_name.getText().toString() + " ?");
        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.out.println(clicked_sub_event_name.getText().toString() + " " + clicked_no_of_participants.getText().toString());
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

    private void getClickedReferences(View item) {
        ViewGroup parent = (ViewGroup) item.getParent();
        clicked_sub_event_name = (TextView) parent.findViewById(R.id.sub_event_name);
        clicked_no_of_participants = (TextView) parent.findViewById(R.id.no_of_participants);
    }

    @Override
    public void onClick(View item) {
        getClickedReferences(item);
        startAddSubEventActivity();
    }

    private void startAddSubEventActivity() {
        Intent i = new Intent(AddEvent.getAddEventAcInstance(), SEParticipants.class);

        i.putExtra(TAG_SUBEVENT_NAME, clicked_sub_event_name.getText().toString());
        i.putExtra(TAG_SUBEVENT_PARTICIPANTS, clicked_no_of_participants.getText().toString());

        AddEvent.getAddEventAcInstance().startActivity(i);
    }


    private void refreshRecyclerView() {

    }

}
