package com.rishabhjain.jvocsportscorner.AddParticipants_AdVh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.R;

/**
 * Created by jainu on 10/6/16.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView name, mem_no, gender, bdate;
    private final CheckBox CB_add_participant;

    ViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_add_participants, parent, false));
        name = (TextView) itemView.findViewById(R.id.list_mem_name);
        mem_no = (TextView) itemView.findViewById(R.id.list_membership_no);
        gender = (TextView) itemView.findViewById(R.id.list_mem_gender);
        bdate = (TextView) itemView.findViewById(R.id.list_mem_bdate);
        CB_add_participant = (CheckBox) itemView.findViewById(R.id.CB_addparticipant);
    }

    void bind(ItemModel itemModel){
        name.setText(itemModel.getName());
        mem_no.setText(itemModel.getMem_no());
        gender.setText(itemModel.getGender());
        bdate.setText(itemModel.getBdate());
    }
}
