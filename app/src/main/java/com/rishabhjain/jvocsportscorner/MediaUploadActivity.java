package com.rishabhjain.jvocsportscorner;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.MediaEIndividual_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.MediaEIndividual_AdVh.ItemModel;

import java.util.ArrayList;
import java.util.List;

import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTDATE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTNAME;

public class MediaUploadActivity extends AppCompatActivity {

    private static MediaUploadActivity activity = null;
    private List<ItemModel> models = null;
    String[] urls;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_upload);
        extras = getIntent().getExtras();
        setToolbar();
        activity = this;
        loadImages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_media, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.add_media_menu:
                startAddMediaAc();
                return true;
        }
        return false;
    }

    private void startAddMediaAc() {


    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(extras.getString(TAG_EVENTNAME) + ", " +extras.getString(TAG_EVENTDATE));
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void loadImages() {
        models = new ArrayList<>();
        urls = getResources().getStringArray(R.array.eventCoverImages);
        for (String url : urls) {
            models.add(new ItemModel(url));
            System.out.println(""+url);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        ContentAdapter adapter = new ContentAdapter(models);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
    }

    public static MediaUploadActivity getMUInstance() {
        return activity;
    }
}
