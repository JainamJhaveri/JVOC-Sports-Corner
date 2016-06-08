package com.rishabhjain.jvocsportscorner.AddEvent_AdVh;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.AddEvent;
import com.rishabhjain.jvocsportscorner.R;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView sub_event_name;
    private final TextView no_of_participants;
    private TextView clicked_sub_event_name, clicked_no_of_participants;
    private final Button cancel_button;

    VectorDrawableCompat indicator = VectorDrawableCompat.create(AddEvent.getAddEventResources(),
            R.drawable.ic_remove_black_24dp, AddEvent.getAddEventTheme());

    ViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_single_sub_event, parent, false));
        sub_event_name = (TextView) itemView.findViewById(R.id.sub_event_name);
        no_of_participants = (TextView) itemView.findViewById(R.id.no_of_participants);
        cancel_button = (Button) itemView.findViewById(R.id.delete_sub_event);
        cancel_button.setOnClickListener(this);
    }

    void bind(ItemModel itemModel) {
        sub_event_name.setText(itemModel.getSub_event_name());
        no_of_participants.setText(itemModel.getNo_of_participants());
        if (indicator != null) {
            indicator.setTint(ResourcesCompat.getColor(
                    AddEvent.getAddEventResources(), R.color.red, AddEvent.getAddEventTheme()));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            cancel_button.setBackground(indicator);
        }
    }

    @Override
    public void onClick(View v) {
        ViewGroup parent;
        if(v.getId() == R.id.delete_sub_event){
            parent = (ViewGroup) v.getParent();
            clicked_sub_event_name = (TextView) parent.findViewById(R.id.sub_event_name);
            clicked_no_of_participants= (TextView) parent.findViewById(R.id.no_of_participants);
            AlertDialog.Builder dialog = new AlertDialog.Builder(AddEvent.getAddEventAcInstance());
            dialog.setTitle("Delete subevent").setMessage("Surely Delete the sub event "+clicked_sub_event_name.getText().toString()+" ?" );
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
        }
    }

    private void refreshRecyclerView() {

    }

    /*
    * public void onClick(View v) {
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

    * */
}
