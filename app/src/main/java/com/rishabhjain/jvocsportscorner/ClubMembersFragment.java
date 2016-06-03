package com.rishabhjain.jvocsportscorner;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClubMembersFragment extends Fragment {


    public ClubMembersFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recyclerview, container, false);

        View view = inflater.inflate(R.layout.fragment_club_members, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, mem_no, gender, bdate;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_club_members, parent, false));
            name = (TextView) itemView.findViewById(R.id.list_mem_name);
            mem_no = (TextView) itemView.findViewById(R.id.list_membership_no);
            gender = (TextView) itemView.findViewById(R.id.list_mem_gender);
            bdate = (TextView) itemView.findViewById(R.id.list_mem_bdate);;
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 18;

        private final String[] names, mem_nos, genders, bdates;


        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            names = resources.getStringArray(R.array.mem_names);
            mem_nos = resources.getStringArray(R.array.membership_nos);
            genders = resources.getStringArray(R.array.mem_genders);
            bdates = resources.getStringArray(R.array.mem_bdates);
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.name.setText(names[position % names.length]);
            holder.mem_no.setText(mem_nos[position % names.length]);
            holder.gender.setText(genders[position % names.length]);
            holder.bdate.setText(bdates[position % names.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }

}
