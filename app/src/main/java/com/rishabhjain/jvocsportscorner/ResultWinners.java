package com.rishabhjain.jvocsportscorner;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.rishabhjain.jvocsportscorner.General.Constants;
import com.rishabhjain.jvocsportscorner.Matches.ResultGameFragment;

import java.util.ArrayList;
import java.util.List;

public class ResultWinners extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ResultWinnersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_winners);

        Bundle extras = getIntent().getExtras();
        setTitle(extras.getString(Constants.TAG_EVENTNAME));

        adapter = new ResultWinnersAdapter(getSupportFragmentManager());
        getReferences();
        initializeToolbar();
        setupActionBar();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }

    private void setupActionBar() {
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getReferences() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter.addFrag(new ResultGameFragment(), "CHESS");
        adapter.addFrag(new ResultGameFragment(), "T.T.");
        adapter.addFrag(new ResultGameFragment(), "CARROM");
        viewPager.setAdapter(adapter);
    }

    private void initializeToolbar() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    private class ResultWinnersAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mFragmentList = new ArrayList<>();
        private List<String> mFragmentTitleList = new ArrayList<>();

        public ResultWinnersAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
