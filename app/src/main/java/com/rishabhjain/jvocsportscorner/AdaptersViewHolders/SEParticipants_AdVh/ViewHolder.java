package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.SEParticipants_AdVh;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.R;
import com.rishabhjain.jvocsportscorner.ViewParticipants.SEParticipants;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
    private final TextView name, mem_no, gender, bdate;
    private TextView clicked_name, clicked_mem_no, clicked_gender, clicked_bdate;

    ViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_club_members, parent, false));
        name = (TextView) itemView.findViewById(R.id.list_mem_name);
        mem_no = (TextView) itemView.findViewById(R.id.list_membership_no);
        gender = (TextView) itemView.findViewById(R.id.list_mem_gender);
        bdate = (TextView) itemView.findViewById(R.id.list_mem_bdate);

        name.setOnLongClickListener(this);
        mem_no.setOnLongClickListener(this);
        gender.setOnLongClickListener(this);
        bdate.setOnLongClickListener(this);
    }

    void bind(ItemModel itemModel){
        name.setText(itemModel.getName());
        mem_no.setText(itemModel.getMem_no());
        gender.setText(itemModel.getGender());
        bdate.setText(itemModel.getBdate());
    }

    @Override
    public boolean onLongClick(View item) {

        if( item.getId() == R.id.rl_item_clubmem ){ // if item area apart from the linearlayouts is clicked
            getClickedReferences((ViewGroup) item);
        }
        else if(  isDirectChild(item) ){    // if any of the linear layouts is clicked but not the textview
            getClickedReferences((ViewGroup) item.getParent());
        }
        else{       // if the textviews inside any of the linear layout is clicked
            getClickedReferences((ViewGroup) item.getParent().getParent());
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(SEParticipants.getSEParticipantsAcInstance());
        dialog.setTitle("Remove participant").setMessage("Surely remove the participant: " + clicked_name.getText().toString() + " ?");
        dialog.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.out.println(clicked_name.getText().toString() + " " + clicked_mem_no.getText().toString());
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

    private void getClickedReferences(ViewGroup parent) {
        System.out.println("over here.. !");
        clicked_name = (TextView) parent.findViewById(R.id.list_mem_name);
        clicked_mem_no = (TextView) parent.findViewById(R.id.list_membership_no);
        clicked_gender = (TextView) parent.findViewById(R.id.list_mem_gender);
        clicked_bdate = (TextView) parent.findViewById(R.id.list_mem_bdate);
    }

    private boolean isDirectChild(View v) {
        ViewGroup parent = (ViewGroup) v.getParent();
        return parent.getId() == R.id.rl_item_clubmem;
    }
}
