package com.rishabhjain.jvocsportscorner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTDATE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTNAME;

public class MediaUploadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_upload);
        Bundle extras = getIntent().getExtras();
        System.out.println(extras.getString(TAG_EVENTDATE) + ", " +extras.getString(TAG_EVENTNAME));
//        startMediaUploadActivity();
    }

//    private void startMediaUploadActivity() {
//        Intent intent = new Intent(this, MultiImageSelectorActivity.class);
//// whether show camera
//        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
//// max select image amount
//        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
//// select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
//        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
//// default select images (support array list)
//        intent.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, defaultDataArray);
//        startActivityForResult(intent, REQUEST_IMAGE);
//    }
}
