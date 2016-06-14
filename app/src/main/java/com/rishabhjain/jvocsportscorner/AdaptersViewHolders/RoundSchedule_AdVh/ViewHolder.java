package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.RoundSchedule_AdVh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.R;

/**
 * Created by jainu on 14/6/16.
 */
public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tv_game_no, tv_p1_name, tv_p1_mem_no, tv_p2_name, tv_p2_mem_no, tv_p1_won, tv_p2_won, tv_p1_lost, tv_p2_lost;
    Button button_p1_wins, button_p2_wins;

    ViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_round_schedule, parent, false));

        tv_game_no = (TextView) itemView.findViewById(R.id.tv_game_no);
        tv_p1_name = (TextView) itemView.findViewById(R.id.tv_p1_name);
        tv_p2_name = (TextView) itemView.findViewById(R.id.tv_p2_name);
        tv_p1_mem_no = (TextView) itemView.findViewById(R.id.tv_p1_mem_no);
        tv_p2_mem_no = (TextView) itemView.findViewById(R.id.tv_p2_mem_no);
        tv_p1_won = (TextView) itemView.findViewById(R.id.tv_p1_won);
        tv_p2_won = (TextView) itemView.findViewById(R.id.tv_p2_won);
        tv_p1_lost = (TextView) itemView.findViewById(R.id.tv_p1_lost);
        tv_p2_lost = (TextView) itemView.findViewById(R.id.tv_p2_lost);

        button_p1_wins = (Button) itemView.findViewById(R.id.button_p1_wins);
        button_p2_wins = (Button) itemView.findViewById(R.id.button_p2_wins);

        button_p1_wins.setOnClickListener(this);
        button_p2_wins.setOnClickListener(this);

    }

    void bind(ItemModel itemModel) {
        tv_game_no.setText(itemModel.getGame_no());
        tv_p1_name.setText(itemModel.getP1_name());
        tv_p2_name.setText(itemModel.getP2_name());
        tv_p1_mem_no.setText(itemModel.getP1_mem_no());
        tv_p2_mem_no.setText(itemModel.getP2_mem_no());
    }

    @Override
    public void onClick(View v) {
        ViewGroup parent = (ViewGroup) v.getParent();
        ViewGroup superparent = (ViewGroup) parent.getParent();

        if( v.getId() == R.id.button_p1_wins){
            Button b_p1_w = (Button) parent.findViewById(R.id.button_p1_wins);
            TextView tv_p1_w = (TextView) parent.findViewById(R.id.tv_p1_won);
            TextView tv_p1_l = (TextView) parent.findViewById(R.id.tv_p1_lost);

            parent.removeView(b_p1_w);
            parent.removeView(tv_p1_l);
            tv_p1_w.setVisibility(View.VISIBLE);


            ViewGroup sibling = (ViewGroup) superparent.getChildAt(1);

            Button b_p2_w = (Button) sibling.findViewById(R.id.button_p2_wins);
            TextView tv_p2_w = (TextView) sibling.findViewById(R.id.tv_p2_won);
            TextView tv_p2_l = (TextView) sibling.findViewById(R.id.tv_p2_lost);

            sibling.removeView(b_p2_w);
            sibling.removeView(tv_p2_w);
            tv_p2_l.setVisibility(View.VISIBLE);
        }
        else if(v.getId() == R.id.button_p2_wins){
            Button b_p2_w = (Button) parent.findViewById(R.id.button_p2_wins);
            TextView tv_p2_w = (TextView) parent.findViewById(R.id.tv_p2_won);
            TextView tv_p2_l = (TextView) parent.findViewById(R.id.tv_p2_lost);

            parent.removeView(b_p2_w);
            parent.removeView(tv_p2_l);
            tv_p2_w.setVisibility(View.VISIBLE);


            ViewGroup sibling = (ViewGroup) superparent.getChildAt(0);

            Button b_p1_w = (Button) sibling.findViewById(R.id.button_p1_wins);
            TextView tv_p1_w = (TextView) sibling.findViewById(R.id.tv_p1_won);
            TextView tv_p1_l = (TextView) sibling.findViewById(R.id.tv_p1_lost);

            sibling.removeView(b_p1_w);
            sibling.removeView(tv_p1_w);
            tv_p1_l.setVisibility(View.VISIBLE);
        }
    }
}
