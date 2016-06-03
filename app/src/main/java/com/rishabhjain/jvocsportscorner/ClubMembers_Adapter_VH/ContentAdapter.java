package com.rishabhjain.jvocsportscorner.ClubMembers_Adapter_VH;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rishabhjain.jvocsportscorner.R;

public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int LENGTH = 18;
        private final String TAG = this.getClass().getSimpleName();
        private final String[] names, mem_nos, genders, bdates;

        private static final int TYPE_ITEM = 0;
        private static final int TYPE_FOOTER= 1;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            names = resources.getStringArray(R.array.mem_names);
            mem_nos = resources.getStringArray(R.array.membership_nos);
            genders = resources.getStringArray(R.array.mem_genders);
            bdates = resources.getStringArray(R.array.mem_bdates);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if( viewType == TYPE_ITEM )
                return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
            else if( viewType == TYPE_FOOTER )
                return new FooterHolder(LayoutInflater.from(parent.getContext()), parent);
            throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if( holder instanceof ViewHolder){
                ViewHolder myholder = (ViewHolder)holder;
                myholder.name.setText(names[position % names.length]);
                myholder.mem_no.setText(mem_nos[position % names.length]);
                myholder.gender.setText(genders[position % names.length]);
                myholder.bdate.setText(bdates[position % names.length]);
            }else if( holder instanceof FooterHolder){
                // last_updated_on textview will be set from this block
                Log.e(TAG, "onBindViewHolder:instance of footer holder " );
            }
        }

        @Override
        public int getItemViewType(int position) {
            Log.e(TAG, "getItemViewType: " + position );
            if( position == LENGTH )
                return TYPE_FOOTER;
            return TYPE_ITEM;
        }

        @Override
        public int getItemCount() {
            return LENGTH + 1;
        }   // + 1 is due to footer
    }