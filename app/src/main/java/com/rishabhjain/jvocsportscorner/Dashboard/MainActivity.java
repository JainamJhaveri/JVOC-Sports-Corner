package com.rishabhjain.jvocsportscorner.Dashboard;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.rishabhjain.jvocsportscorner.ClubMembersFragment;
import com.rishabhjain.jvocsportscorner.Events.EventsFragment;
import com.rishabhjain.jvocsportscorner.Matches.MainFragment;
import com.rishabhjain.jvocsportscorner.Media.MediaFragment;
import com.rishabhjain.jvocsportscorner.R;
import com.rishabhjain.jvocsportscorner.ViewParticipants.ViewPEFragment;

public class MainActivity extends AppCompatActivity {

//    private final String TAG = this.getClass().getSimpleName();
    private DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    Fragment fragment;
    private String title;
    private static MainActivity activity;

    public static MainActivity getMainAcInstance(){
        return activity;
    }

    @Override
    public void onBackPressed() {
        ViewGroup v = (ViewGroup) findViewById(R.id.main_container);

        assert v != null;
        if(  ( R.id.fragment_dashboard == v.getChildAt(0).getId() ) ){
            System.out.println(".. backpressed from dashboard .. ");
            super.onBackPressed();
            return;
        }

        System.out.println(".. backpressed from non-dashboard fragment.. " +fragment.getClass().getSimpleName());
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new DashboardFragment()).commit();
        setTitle("Dashboard");
        navigationView.getMenu().getItem(0).setChecked(true);

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.closeDrawers();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Dashboard");
        addInitialDashboardFragment(savedInstanceState);

        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        setMenuIconInActionBar();
        setNavigationView();
        activity = this;
    }

    private void addInitialDashboardFragment(Bundle savedInstanceState) {
        if( savedInstanceState == null) {
            if (fragment == null) {
                System.out.println(".. . here ..");
                fragment = new DashboardFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new DashboardFragment()).commit();
                setTitle("Dashboard");
            }
        }else{
            setTitle("Dashboard");
        }
    }

    private void setMenuIconInActionBar() {
        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            if (indicator != null) {
                indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.white,getTheme()));
            }
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if( id == android.R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNavigationView() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(   // Set behavior of Navigation drawer
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        switch (menuItem.getItemId()){
                            case R.id.dashboard_menu:
                                title = "Dashboard";
                                fragment = new DashboardFragment();
                                break;
                            case R.id.clubmembers_menu:
                                title = "Club Members";
                                fragment = new ClubMembersFragment();
                                break;
                            case R.id.events_menu:
                                title = "Events";
                                fragment = new EventsFragment();
                                break;
                            case R.id.media_menu:
                                title = "Media";
                                fragment = new MediaFragment();
                                break;
                            case R.id.matches_menu:
                                title = "Matches";
                                fragment = new MainFragment();
                                break;
                            case R.id.viewparticipants_menu:
                                title = "View Participants";
                                fragment = new ViewPEFragment();
                                break;

                        }
                        setTitle(title);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
                        return true;
                    }
                });
        navigationView.getMenu().getItem(0).setChecked(true);
    }

}
