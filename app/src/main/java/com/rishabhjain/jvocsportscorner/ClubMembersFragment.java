package com.rishabhjain.jvocsportscorner;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClubMembersFragment extends Fragment {


    public ClubMembersFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_club_mem_fragment, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_club_members, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
        return view;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, mem_no, gender, bdate;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_club_members, parent, false));
            name = (TextView) itemView.findViewById(R.id.list_mem_name);
            mem_no = (TextView) itemView.findViewById(R.id.list_membership_no);
            gender = (TextView) itemView.findViewById(R.id.list_mem_gender);
            bdate = (TextView) itemView.findViewById(R.id.list_mem_bdate);
        }
    }

    public static class FooterHolder extends RecyclerView.ViewHolder {
        public TextView tv_updated_on;

        public FooterHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.layout_last_updated, parent, false));
            tv_updated_on = (TextView) itemView.findViewById(R.id.tv_updated_on);
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        // Set numbers of List in RecyclerView.
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
            if( holder instanceof ViewHolder ){
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

}
