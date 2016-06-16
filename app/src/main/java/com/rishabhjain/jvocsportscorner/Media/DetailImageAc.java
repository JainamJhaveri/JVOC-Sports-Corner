package com.rishabhjain.jvocsportscorner.Media;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.rishabhjain.jvocsportscorner.R;

public class DetailImageAc extends AppCompatActivity {

    ViewPager pager;
    int images[] = new int[]{R.drawable.matches, R.drawable.media, R.drawable.events, R.drawable.participants};
    ImgViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image);

//        String position = getIntent().getExtras().getString(Constants.TAG_IMG_POSITION);
        getReferences();
        setupViewPager();
//        loadImageAt(Integer.parseInt(position));
    }

    private void getReferences() {
        pager = (ViewPager) findViewById(R.id.pager);
    }

    private void setupViewPager() {
        adapter = new ImgViewPagerAdapter(this, images);
        pager.setAdapter(adapter);
    }

    private void loadImageAt(int position) {

    }
}
