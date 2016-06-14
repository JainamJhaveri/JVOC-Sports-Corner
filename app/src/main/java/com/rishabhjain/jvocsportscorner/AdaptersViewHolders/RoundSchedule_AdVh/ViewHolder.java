package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.RoundSchedule_AdVh;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.R;

import static com.rishabhjain.jvocsportscorner.Matches.RoundActivity.getRSAcInstance;

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

        if (v.getId() == R.id.button_p1_wins) {
            final ViewGroup parent = (ViewGroup) v.getParent();
            final ViewGroup gparent = (ViewGroup) parent.getParent();
            final ViewGroup greatgparent = (ViewGroup) gparent.getParent();

            TextView tv_p1_name = (TextView) greatgparent.findViewById(R.id.tv_p1_name);
            AlertDialog.Builder dialog = new AlertDialog.Builder(getRSAcInstance());
            dialog.setTitle("Result").setMessage(tv_p1_name.getText().toString() + " wins ? \nThis action can't be undone.");

            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    ViewGroup sibling = (ViewGroup) gparent.getChildAt(1);

                    Button b_p1_w = (Button) parent.findViewById(R.id.button_p1_wins);
                    TextView tv_p1_w = (TextView) parent.findViewById(R.id.tv_p1_won);
                    TextView tv_p1_l = (TextView) parent.findViewById(R.id.tv_p1_lost);

                    Button b_p2_w = (Button) sibling.findViewById(R.id.button_p2_wins);
                    TextView tv_p2_w = (TextView) sibling.findViewById(R.id.tv_p2_won);
                    TextView tv_p2_l = (TextView) sibling.findViewById(R.id.tv_p2_lost);

                    updateWinLostViews(b_p1_w, tv_p1_w, tv_p1_l, b_p2_w, tv_p2_w, tv_p2_l, sibling, parent);

                }
            });
            dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.create();
            dialog.show();

        } else if (v.getId() == R.id.button_p2_wins) {
            final ViewGroup parent = (ViewGroup) v.getParent();
            final ViewGroup gparent = (ViewGroup) parent.getParent();
            final ViewGroup greatgparent = (ViewGroup) gparent.getParent();

            TextView tv_p2_name = (TextView) greatgparent.findViewById(R.id.tv_p2_name);
            AlertDialog.Builder dialog = new AlertDialog.Builder(getRSAcInstance());
            dialog.setTitle("Result").setMessage(tv_p2_name.getText().toString() + " wins ? \nThis action can't be undone.");

            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    ViewGroup sibling = (ViewGroup) gparent.getChildAt(0);

                    Button b_p2_w = (Button) parent.findViewById(R.id.button_p2_wins);
                    TextView tv_p2_w = (TextView) parent.findViewById(R.id.tv_p2_won);
                    TextView tv_p2_l = (TextView) parent.findViewById(R.id.tv_p2_lost);

                    Button b_p1_w = (Button) sibling.findViewById(R.id.button_p1_wins);
                    TextView tv_p1_w = (TextView) sibling.findViewById(R.id.tv_p1_won);
                    TextView tv_p1_l = (TextView) sibling.findViewById(R.id.tv_p1_lost);

                    updateWinLostViews(b_p2_w, tv_p2_w, tv_p2_l, b_p1_w, tv_p1_w, tv_p1_l, sibling, parent);

                }
            });
            dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.create();
            dialog.show();

        }
    }

    public void updateWinLostViews(Button winner_winButton, TextView winner_winTV, TextView winner_lostTV,
                                   Button loser_winButton, TextView loser_winTV, TextView loser_lostTV,
                                   ViewGroup sibling, ViewGroup parent) {

        parent.removeView(winner_winButton);
        parent.removeView(winner_lostTV);
        winner_winTV.setVisibility(View.VISIBLE);

        sibling.removeView(loser_winButton);
        sibling.removeView(loser_winTV);
        loser_lostTV.setVisibility(View.VISIBLE);

    }


}
