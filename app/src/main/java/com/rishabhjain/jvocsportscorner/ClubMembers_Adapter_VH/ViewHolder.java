package com.rishabhjain.jvocsportscorner.ClubMembers_Adapter_VH;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.R;

public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, mem_no, gender, bdate;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_club_members, parent, false));
            name = (TextView) itemView.findViewById(R.id.list_mem_name);
            mem_no = (TextView) itemView.findViewById(R.id.list_membership_no);
            gender = (TextView) itemView.findViewById(R.id.list_mem_gender);
            bdate = (TextView) itemView.findViewById(R.id.list_mem_bdate);
        }
    }