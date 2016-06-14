package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.ResultsWinner_AdVh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView tv_1st_name, tv_2nd_name, tv_1st_mem_no, tv_2nd_mem_no, tv_se_name;

    ViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_result_game, parent, false));
        getReferences();
    }

    private void getReferences() {
        tv_se_name = (TextView) itemView.findViewById(R.id.tv_sub_event_name);
        tv_1st_name = (TextView) itemView.findViewById(R.id.tv_1st_name);
        tv_1st_mem_no = (TextView) itemView.findViewById(R.id.tv_1st_mem_no);
        tv_2nd_name = (TextView) itemView.findViewById(R.id.tv_2nd_name);
        tv_2nd_mem_no = (TextView) itemView.findViewById(R.id.tv_2nd_mem_no);
    }

    void bind(ItemModel model){
        tv_se_name.setText(model.getSe_name());
        tv_1st_name.setText(model.getwName());
        tv_1st_mem_no.setText(model.getwMemNo());
        tv_2nd_name.setText(model.getrName());
        tv_2nd_mem_no.setText(model.getrMemNo());
    }
}
