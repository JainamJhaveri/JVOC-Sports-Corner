package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.Dashboard_AdVh;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rishabhjain.jvocsportscorner.Dashboard.MainActivity;
import com.rishabhjain.jvocsportscorner.Events.EventsFragment;
import com.rishabhjain.jvocsportscorner.Matches.MainFragment;
import com.rishabhjain.jvocsportscorner.Media.MediaFragment;
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

    ViewHolder(LayoutInflater inflater, final ViewGroup parent) {
        super(inflater.inflate(R.layout.item_dashboard, parent, false));
        picture = (ImageView) itemView.findViewById(R.id.tile_picture);
        name = (TextView) itemView.findViewById(R.id.tile_title);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int nav_index = 0;

                Log.e(TAG, "onClick: "+getAdapterPosition() );
                switch (getAdapterPosition()){
                    case 0:
                        title = "Events";
                        fragment = new EventsFragment();
                        nav_index = 2;
                        break;
                    case 1:
                        title = "Media";
                        fragment = new MediaFragment();
                        nav_index = 3;
                        break;
                    case 2:
                        title = "Matches";
                        fragment = new MainFragment();
                        nav_index = 4;
                        break;
                    case 3:
                        title = "Participants";
                        fragment = new ViewPEFragment();
                        nav_index = 5;
                        break;
                }
                MainActivity m= MainActivity.getMainAcInstance();
                setSPTitle(m, title);
                m.setTitle(getSPTitle( m ));
                m.getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();

                ViewGroup nav_parent = (ViewGroup) parent.getParent().getParent().getParent().getParent();
                NavigationView navigationView = (NavigationView) nav_parent.findViewById(R.id.nav_view);
                navigationView.getMenu().getItem(nav_index).setChecked(true);
            }
        });
    }

    void bind(ItemModel itemModel){
        name.setText(itemModel.getName());
        picture.setImageDrawable(itemModel.getPicture());
    }

}