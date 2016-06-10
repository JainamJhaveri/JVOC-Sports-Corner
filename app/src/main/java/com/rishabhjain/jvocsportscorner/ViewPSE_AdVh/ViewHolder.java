package com.rishabhjain.jvocsportscorner.ViewPSE_AdVh;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.R;
import com.rishabhjain.jvocsportscorner.ViewPSE;
import com.rishabhjain.jvocsportscorner.ViewPSEP;

import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_SUBEVENT_NAME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_SUBEVENT_PARTICIPANTS;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private final TextView sub_event_name;
    private final TextView no_of_participants;
    private TextView clicked_sub_event_name, clicked_no_of_participants;

    ViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_single_sub_event, parent, false));
        sub_event_name = (TextView) itemView.findViewById(R.id.sub_event_name);
        no_of_participants = (TextView) itemView.findViewById(R.id.no_of_participants);

        sub_event_name.setOnClickListener(this);
        no_of_participants.setOnClickListener(this);
    }

    void bind(ItemModel itemModel) {
        sub_event_name.setText(itemModel.getSub_event_name());
        no_of_participants.setText(itemModel.getNo_of_participants());
    }


    private void getClickedReferences(View item) {
        ViewGroup parent = (ViewGroup) item.getParent();
        clicked_sub_event_name = (TextView) parent.findViewById(R.id.sub_event_name);
        clicked_no_of_participants = (TextView) parent.findViewById(R.id.no_of_participants);
    }

    @Override
    public void onClick(View item) {
        getClickedReferences(item);
        Intent i = new Intent(ViewPSE.getViewPSEAcInstance(), ViewPSEP.class);
        i.putExtra(TAG_SUBEVENT_NAME, clicked_sub_event_name.getText().toString());
        i.putExtra(TAG_SUBEVENT_PARTICIPANTS, clicked_no_of_participants.getText().toString());
        ViewPSE.getViewPSEAcInstance().startActivity(i);
    }

}
