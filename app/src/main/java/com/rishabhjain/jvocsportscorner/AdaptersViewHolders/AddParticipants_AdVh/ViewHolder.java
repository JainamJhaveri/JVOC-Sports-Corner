package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.AddParticipants_AdVh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.R;

/**
 * Created by jainu on 10/6/16.
 */
public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView name, mem_no, gender, bdate;
    private final CheckBox CB_add_participant;


    ViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_add_participants, parent, false));
        name = (TextView) itemView.findViewById(R.id.list_mem_name);
        mem_no = (TextView) itemView.findViewById(R.id.list_membership_no);
        gender = (TextView) itemView.findViewById(R.id.list_mem_gender);
        bdate = (TextView) itemView.findViewById(R.id.list_mem_bdate);
        CB_add_participant = (CheckBox) itemView.findViewById(R.id.CB_addparticipant);
        CB_add_participant.setOnClickListener(this);
    }

    void bind(ItemModel itemModel){
        name.setText(itemModel.getName());
        mem_no.setText(itemModel.getMem_no());
        gender.setText(itemModel.getGender());
        bdate.setText(itemModel.getBdate());
        CB_add_participant.setChecked(false);
        CB_add_participant.setTag(itemModel);
    }

    @Override
    public void onClick(View v) {
        if( v.getId() == R.id.CB_addparticipant ){  // checkbox is clicked
            CheckBox cb = (CheckBox) v;
            ItemModel model = (ItemModel) cb.getTag();
            model.setSelected( cb.isChecked() );
            System.out.println("checkbox selected: " +model.getName() + " checked: " +cb.isChecked());
        }
    }
}
