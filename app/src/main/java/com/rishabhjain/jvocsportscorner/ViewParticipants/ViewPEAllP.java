package com.rishabhjain.jvocsportscorner.ViewParticipants;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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

        initTable();
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

    private void setFieldValues() {
        Bundle extras = getIntent().getExtras();
        setTitle(extras.getString(TAG_EVENTNAME) + ", " + extras.getString(TAG_EVENTDATE));
//        System.out.println( extras.getString(TAG_EVENTDATE) );
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


    // Sr no, participant's name, participant's mem_no, se1, se2, .., seN

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initTable() {
        String[] firstRow = {"Sr.No", "Participant Name", "Mem No"};
        Resources r = getResources();
        String[] subevents = r.getStringArray(R.array.sub_event_names);
        String lastSrNo = r.getString(R.string.noofEventParticipants);
        String[] participants = r.getStringArray(R.array.mem_names);
        String[] mem_nos = r.getStringArray(R.array.membership_nos);
        int totalSubEvents = 2;
        boolean[][] participated = {{true, false}, {false, true}};

        TableLayout tableLayout = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = (TableRow) findViewById(R.id.tblrow0);

        /*initializing the first row*/
        for (String subevent : subevents) {
            TextView tv = new TextView(this);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new TableRow.LayoutParams
                    (130, TableRow.LayoutParams.MATCH_PARENT));
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(14);
            tv.setText(subevent);
            tbrow0.addView(tv);
        }

        /*populating table with data*/
        for (int i = 0; i < Integer.parseInt(lastSrNo); i++) {
            TableRow tbrow = new TableRow(this);

            if (i % 2 == 0) tbrow.setBackgroundResource(R.drawable.light_grey_rounded_corner);

            TextView t1v = new TextView(this);
            t1v.setText("" + (i + 1));
            t1v.setGravity(Gravity.CENTER);
            t1v.setTextColor(Color.BLACK);
            tbrow.addView(t1v);

            TextView t2v = new TextView(this);
            t2v.setText(participants[i % 8]);
            t2v.setGravity(Gravity.CENTER);
            t2v.setTextColor(Color.BLACK);
            tbrow.addView(t2v);

            TextView t3v = new TextView(this);
            t3v.setText(mem_nos[i % 8]);
            t3v.setGravity(Gravity.CENTER);
            t3v.setTextColor(Color.BLACK);
            tbrow.addView(t3v);

            for (int j = 0; j < totalSubEvents; j++) {
                TextView t4v = new TextView(this);

                if (participated[i % 2][j])
                    t4v.setText(Html.fromHtml("&#x2713;"));
                t4v.setGravity(Gravity.CENTER);
                t4v.setTextColor(Color.GREEN);
                tbrow.addView(t4v);
            }
            tableLayout.addView(tbrow);

        }
    }

}
