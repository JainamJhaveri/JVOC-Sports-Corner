package com.rishabhjain.jvocsportscorner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rishabhjain.jvocsportscorner.AddEvent_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.AddEvent_AdVh.ItemModel;
import com.rishabhjain.jvocsportscorner.General.Constants;
import com.rishabhjain.jvocsportscorner.General.DividerItemDecoration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.rishabhjain.jvocsportscorner.General.Constants.*;

public class AddEvent extends AppCompatActivity {

    Button editDate, startTimeEdit, endTimeEdit;
    EditText et_event_name, et_event_venue;
    TextView dateTv, startTimeTv, endTimeTv, noOfParticipantsTV;
    private List<ItemModel> models;
    private String[] sub_event_names, sub_event_participants;
    private static AddEvent activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getReferences();
        setFieldValues();
        setupActionBar();
        setupEditButtons();
        activity = this;
        setupRecyclerView();
    }

    private void setFieldValues() {
        Bundle extras = getIntent().getExtras();
        setTitle(extras.getString(TAG_ADDEVENTTITILE));

        et_event_name.setText(extras.getString(TAG_EVENTNAME));
        et_event_venue.setText(extras.getString(TAG_EVENTVENUE));
        dateTv.setText(extras.getString(TAG_EVENTDATE));
        startTimeTv.setText(extras.getString(TAG_STARTTIME));
        endTimeTv.setText(extras.getString(TAG_ENDTIME));
        noOfParticipantsTV.setText(String.valueOf(extras.getInt(TAG_UNIQUEPARTICIPANTS)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_subevent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_new_sub_event_menu:
                Intent intent = new Intent(this, AddSubEvent.class);
                startActivityForResult(intent, ADD_SUB_EVENT_REQ_CODE);
                return true;
            case android.R.id.home:
                setResult(EVENT_NOT_ADDED);
                finish();
                return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == SUB_EVENT_ADDED) {
            if (requestCode == ADD_SUB_EVENT_REQ_CODE) {
                // recyclerview should be updated here
            }
        }
        else if ( resultCode == SUB_EVENT_NOT_ADDED){
            // called when back button is pressed from AddSubEvent class
        }

    }

    private void initializeList() {
        models = new ArrayList<>();
        sub_event_names = getResources().getStringArray(R.array.sub_event_names);
        sub_event_participants = getResources().getStringArray(R.array.sub_event_participants);
        for (int i = 0; i < sub_event_names.length; i++) {
            models.add(new ItemModel(sub_event_names[i], sub_event_participants[i]));
        }
    }

    private void setupRecyclerView() {
        initializeList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        ContentAdapter adapter = new ContentAdapter(models);
        assert recyclerView != null;
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
    }

    private void setupEditButtons() {
        VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(), R.drawable.ic_edit_black_24dp, getTheme());
        if (indicator != null) {
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.dark_grey, getTheme()));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            editDate.setBackground(indicator);
            startTimeEdit.setBackground(indicator);
            endTimeEdit.setBackground(indicator);
        }

    }

    private void getReferences() {
        editDate = (Button) findViewById(R.id.editDate);
        startTimeEdit = (Button) findViewById(R.id.startTimeEdit);
        endTimeEdit = (Button) findViewById(R.id.endTimeEdit);
        et_event_name = (EditText) findViewById(R.id.et_event_name);
        et_event_venue = (EditText) findViewById(R.id.et_event_venue);
        dateTv = (TextView) findViewById(R.id.dateTV);
        startTimeTv = (TextView) findViewById(R.id.startTimeTV);
        endTimeTv = (TextView) findViewById(R.id.endTimeTV);
        noOfParticipantsTV = (TextView) findViewById(R.id.no_of_participants);
    }

    private void setupActionBar() {
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void eventDateClicked(View view) {
        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd-MM-yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                dateTv.setText(sdf.format(myCalendar.getTime()));
            }

        };

        new DatePickerDialog(AddEvent.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    @Override
    public void onBackPressed() {
        setResult(EVENT_NOT_ADDED);
        finish();
    }

    public void eventDoneClicked(View view) {
        if (validateInputFields()) {
            setResult(EVENT_ADDED);
            finish();
        } else {
            Toast.makeText(AddEvent.this, "Input fields cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void startTimeClicked(View view) {
        final Calendar mcurrentTime = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                mcurrentTime.set(Calendar.HOUR_OF_DAY, hour);
                mcurrentTime.set(Calendar.MINUTE, minute);
                String format;
                if (hour == 0) {
                    hour = 12;
                    format = "AM";
                } else if (hour == 12) {
                    format = "PM";
                } else if (hour > 12) {
                    hour -= 12;
                    format = "PM";
                } else {
                    format = "AM";
                }

                String dispHour = String.valueOf(hour), dispMinute = String.valueOf(minute);
                if (hour < 10)
                    dispHour = "0" + hour;
                if (minute < 10)
                    dispMinute = "0" + minute;
                startTimeTv.setText(dispHour + ":" + dispMinute + " " + format);
            }
        };

        new TimePickerDialog(AddEvent.this, time, mcurrentTime
                .get(Calendar.HOUR_OF_DAY), mcurrentTime.get(Calendar.MINUTE),
                false).show();
    }

    public void endTimeClicked(View view) {
        final Calendar mcurrentTime = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                mcurrentTime.set(Calendar.HOUR_OF_DAY, hour);
                mcurrentTime.set(Calendar.MINUTE, minute);
                String format;
                if (hour == 0) {
                    hour = 12;
                    format = "AM";
                } else if (hour == 12) {
                    format = "PM";
                } else if (hour > 12) {
                    hour -= 12;
                    format = "PM";
                } else {
                    format = "AM";
                }

                String dispHour = String.valueOf(hour), dispMinute = String.valueOf(minute);
                if (hour < 10)
                    dispHour = "0" + hour;
                if (minute < 10)
                    dispMinute = "0" + minute;
                endTimeTv.setText(dispHour + ":" + dispMinute + " " + format);
            }
        };

        new TimePickerDialog(AddEvent.this, time, mcurrentTime
                .get(Calendar.HOUR_OF_DAY), mcurrentTime.get(Calendar.MINUTE),
                false).show();
    }

    private boolean validateInputFields() {
        if (isEmpty(et_event_name) || isEmpty(et_event_venue))
            return false;
        return true;
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    public static Resources.Theme getAddEventTheme() {
        return activity.getTheme();
    }

    public static Resources getAddEventResources() {
        return activity.getResources();
    }

    public static AddEvent getAddEventAcInstance() {
        return activity;
    }

}