package com.rishabhjain.jvocsportscorner;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rishabhjain.jvocsportscorner.ClubMembers_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.ClubMembers_AdVh.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class ClubMembersFragment extends Fragment implements SearchView.OnQueryTextListener {

    private String[] names, mem_nos, genders, bdates;
    private List<ItemModel> models = null;
    ContentAdapter adapter;
    RecyclerView recyclerView;

    public ClubMembersFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        initializeList();
    }

    /* names, mem_nos, genders, bdates will be fetched through this method
        and they are passed to ItemModel list object
        which will then be passed to ContentAdapter to initialize the recyclerview
     */

    private void initializeList() {
        models = new ArrayList<>();
        names = getContext().getResources().getStringArray(R.array.mem_names);
        mem_nos = getContext().getResources().getStringArray(R.array.membership_nos);
        genders = getContext().getResources().getStringArray(R.array.mem_genders);
        bdates = getContext().getResources().getStringArray(R.array.mem_bdates);

        for( int i=0; i<names.length; i++ ){
            models.add(new ItemModel(names[i], mem_nos[i], genders[i], bdates[i]));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_club_mem, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_club_members, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        adapter = new ContentAdapter(models);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    // setting filtered list to the adapter
    @Override
    public boolean onQueryTextChange(String query) {
        final List<ItemModel> filteredModelList = filter(models, query);
        adapter.animateTo(filteredModelList);
        recyclerView.scrollToPosition(0);
        return true;
    }


    /*
    * converting the search query to lowercase and
     * searching the query against all textview items of ItemModel ArrayList
    * */
    private List<ItemModel> filter(List<ItemModel> models, String query) {
        query = query.toLowerCase();

        final List<ItemModel> filteredModelList = new ArrayList<>();
        for (ItemModel model : models) {
            final String name_text = model.getName().toLowerCase();
            final String bdate_text = model.getBdate().toLowerCase();
            final String gender_text = model.getGender().toLowerCase();
            final String memno_text = model.getMem_no().toLowerCase();
            if (name_text.contains(query) || bdate_text.contains(query)
                    || gender_text.contains(query) || memno_text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

}
