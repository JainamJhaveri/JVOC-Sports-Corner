package com.rishabhjain.jvocsportscorner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTNAME;

public class ViewPEAllP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_peall_p);
        setTitle(getIntent().getExtras().getString(TAG_EVENTNAME));
    }
}
