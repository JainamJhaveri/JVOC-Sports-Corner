package com.rishabhjain.jvocsportscorner.General;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Constants {

    public static final String TAG_EVENTNAME = "event_name";
    public static final String TAG_EVENTVENUE = "event_venue";
    public static final String TAG_EVENTDATE = "event_date";
    public static final String TAG_STARTTIME = "start_time";
    public static final String TAG_ENDTIME = "end_time";
    public static final String TAG_UNIQUEPARTICIPANTS = "unique_participants";
    public static final String TAG_ADDEVENTTITILE = "add_event_title";
    public static final String TAG_SUBEVENT_NAME = "sub_event_name";
    public static final String TAG_SUBEVENT_PARTICIPANTS = "sub_event_participants";
    public static final String TAG_IMG_POSITION = "img_position";

    public static final String DEFAULT_EVENTNAME = "";
    public static final String DEFAULT_EVENTVENUE = "";
    public static final String DEFAULT_EVENTDATE = getMyCurrentDate();
    public static final String DEFAULT_STARTTIME = getMyCurrentTime();
    public static final String DEFAULT_ENDTIME = getMyCurrentTime();
    public static final String DEFAULT_UNIQUEPARTICIPANTS = "0";
    public static final String DEFAULT_ADDEVENTTITLE = "Add Event";

    public static final int SUB_EVENT_ADDED = 100;
    public static final int EVENT_ADDED = 101;
    public static final int PARTICIPANTS_ADDED = 102;

    public static final int SUB_EVENT_NOT_ADDED = 400;
    public static final int EVENT_NOT_ADDED = 401;
    public static final int PARTICIPANTS_NOT_ADDED = 402;

    public static final int ADD_EVENT_REQ_CODE = 200;
    public static final int ADD_SUB_EVENT_REQ_CODE = 201;
    public static final int ADD_PARTICIPANTS_REQ_CODE = 202;

    private static String getMyCurrentDate() {
        Calendar myCalendar = Calendar.getInstance();
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        System.out.println(sdf.format(myCalendar.getTime()));
        return sdf.format(myCalendar.getTime());
    }

    private static String getMyCurrentTime(){
        Calendar myCalendar = Calendar.getInstance();
        int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = myCalendar.get(Calendar.MINUTE);
        int format = myCalendar.get(Calendar.AM_PM);

        String dispHour = String.valueOf(hour), dispMinute = String.valueOf(minute);
        if (hour < 10)
            dispHour = "0" + hour;
        if (minute < 10)
            dispMinute = "0" + minute;
        String am_pm = ( format == 0 ) ? "AM" : "PM";
        return dispHour + ":" + dispMinute + " " +am_pm ;
    }

}
