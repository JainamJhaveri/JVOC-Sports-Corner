package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.Dashboard_AdVh;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.Events.EventsFragment;
import com.rishabhjain.jvocsportscorner.Dashboard.MainActivity;
import com.rishabhjain.jvocsportscorner.Matches.MainFragment;
import com.rishabhjain.jvocsportscorner.MediaFragment;
import com.rishabhjain.jvocsportscorner.R;
import com.rishabhjain.jvocsportscorner.ViewParticipants.ViewPEFragment;

import static com.rishabhjain.jvocsportscorner.General.MyPreferences.getSPTitle;
import static com.rishabhjain.jvocsportscorner.General.MyPreferences.setSPTitle;

public class ViewHolder extends RecyclerView.ViewHolder {
    private final String TAG = this.getClass().getSimpleName();
    private ImageView picture;
    private TextView name;
    Fragment fragment;
    String title;

    ViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_dashboard, parent, false));
        picture = (ImageView) itemView.findViewById(R.id.tile_picture);
        name = (TextView) itemView.findViewById(R.id.tile_title);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: "+getAdapterPosition() );
                switch (getAdapterPosition()){
                    case 0:
                        title = "Events";
                        fragment = new EventsFragment();
                        break;
                    case 1:
                        title = "Media";
                        fragment = new MediaFragment();
                        break;
                    case 2:
                        title = "Matches";
                        fragment = new MainFragment();
                        break;
                    case 3:
                        title = "Participants";
                        fragment = new ViewPEFragment();
                        break;
                }
                MainActivity m= MainActivity.getMainAcInstance();
                setSPTitle(m, title);
                m.setTitle(getSPTitle( m ));
                m.getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
            }
        });
    }

    void bind(ItemModel itemModel){
        name.setText(itemModel.getName());
        picture.setImageDrawable(itemModel.getPicture());
    }

}