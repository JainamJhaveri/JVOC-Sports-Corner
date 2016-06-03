package com.rishabhjain.jvocsportscorner.Dashboard_Adapter_VH;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rishabhjain.jvocsportscorner.R;

public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {

        private static final int LENGTH = 4;
        private final String TAG = this.getClass().getSimpleName();
        private final String[] mNames;
        private final Drawable[] mPictures;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mNames = resources.getStringArray(R.array.dashBoardNames);
            TypedArray a = resources.obtainTypedArray(R.array.dashBoardImages);
            mPictures = new Drawable[a.length()];
            for (int i = 0; i < mPictures.length; i++) {
                mPictures[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.name.setText(mNames[position]);
            holder.picture.setImageDrawable(mPictures[position]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }