package com.rishabhjain.jvocsportscorner.Events;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rishabhjain.jvocsportscorner.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.rishabhjain.jvocsportscorner.General.Constants.EVENT_ADDED;
import static com.rishabhjain.jvocsportscorner.General.Constants.EVENT_EDITED;
import static com.rishabhjain.jvocsportscorner.General.Constants.EVENT_NOT_ADDED;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_ADDEVENTTITILE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EDITFLAG;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EDIT_POSITION;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_ENDTIME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTDATE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTNAME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTVENUE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_STARTTIME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_UNIQUEPARTICIPANTS;

public class AddEvent extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private String no_of_participants;
    Button editDate, startTimeEdit, endTimeEdit;
    EditText et_event_name, et_event_venue;
    TextView dateTv, startTimeTv, endTimeTv;
    boolean editFlag = false;
    int edit_position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        setupActionBar();
        getReferences();
        setFieldValues();
        setupEditButtons();
    }

    private void setFieldValues() {
        Bundle extras = getIntent().getExtras();
        setTitle(extras.getString(TAG_ADDEVENTTITILE));
        et_event_name.setText(extras.getString(TAG_EVENTNAME));
        et_event_venue.setText(extras.getString(TAG_EVENTVENUE));
        dateTv.setText(extras.getString(TAG_EVENTDATE));
        startTimeTv.setText(extras.getString(TAG_STARTTIME));
        endTimeTv.setText(extras.getString(TAG_ENDTIME));
        edit_position = extras.getInt(TAG_EDIT_POSITION);
        editFlag = extras.getBoolean(TAG_EDITFLAG);
        no_of_participants = extras.getString(TAG_UNIQUEPARTICIPANTS);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(EVENT_NOT_ADDED);
                finish();
                return true;
        }
        return false;
    }

    private void setupEditButtons() {
        VectorDrawableCompat indicator = VectorDrawableCompat.create(getResources(), R.drawable.ic_edit_black_24dp, getTheme());
        if (indicator != null) {
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.medium_grey, getTheme()));
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
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                dateTv.setText(sdf.format(myCalendar.getTime()));
            }

        };

        new DatePickerDialog(AddEvent.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    public void eventDoneClicked(View view) {
        if (validateInputFields() && validateTimeFields()) {
            Intent output = new Intent();
            output.putExtra(TAG_EVENTNAME, et_event_name.getText().toString());
            output.putExtra(TAG_EVENTVENUE, et_event_venue.getText().toString());
            output.putExtra(TAG_EVENTDATE, dateTv.getText().toString());
            output.putExtra(TAG_STARTTIME, startTimeTv.getText().toString());
            output.putExtra(TAG_ENDTIME, endTimeTv.getText().toString());

            if (editFlag) {       // add event ac was clicked for editing mode
                output.putExtra(TAG_EDIT_POSITION, edit_position);
                output.putExtra(TAG_UNIQUEPARTICIPANTS, no_of_participants);
                editFlag = false;
                edit_position = -1;
                setResult(EVENT_EDITED, output);
                finish();
                return;
            }

            setResult(EVENT_ADDED, output);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(EVENT_NOT_ADDED);
        finish();
    }

    private boolean validateTimeFields() {
        String startTimeString = startTimeTv.getText().toString();
        String endTimeString = endTimeTv.getText().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();

        Date startdate = null, enddate = null;
        try {
            startdate = sdf.parse(startTimeString);
            enddate = sdf.parse(endTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        startCal.setTime(startdate);
        endCal.setTime(enddate);

        if (startCal.before(endCal)) {
            return true;
        }
        Toast.makeText(AddEvent.this, "Start time should be smaller than end time", Toast.LENGTH_SHORT).show();

        return false;
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
        if (isEmpty(et_event_name) || isEmpty(et_event_venue)) {
            Toast.makeText(AddEvent.this, "Input fields cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

}