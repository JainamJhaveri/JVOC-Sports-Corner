package com.rishabhjain.jvocsportscorner.ViewParticipants;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.rishabhjain.jvocsportscorner.R;

import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTDATE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTNAME;

public class ViewPEAllP extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_peall_p);
        getReferences();
        initializeToolbar();
        setFieldValues();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }

    private void setFieldValues() {
        Bundle extras = getIntent().getExtras();
        setTitle(extras.getString(TAG_EVENTNAME));
        System.out.println( extras.getString(TAG_EVENTDATE) );
    }

    private void initializeToolbar() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getReferences() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }


}
