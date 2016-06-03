package com.rishabhjain.jvocsportscorner.ClubMembers_Adapter_VH;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.R;

public class FooterHolder extends RecyclerView.ViewHolder {
        public TextView tv_updated_on;

        public FooterHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.layout_last_updated, parent, false));
            tv_updated_on = (TextView) itemView.findViewById(R.id.tv_updated_on);
        }
    }