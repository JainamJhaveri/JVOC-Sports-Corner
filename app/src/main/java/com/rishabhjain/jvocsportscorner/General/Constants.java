package com.rishabhjain.jvocsportscorner.General;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Constants {

    public static final String TAG_EVENTNAME = "event_name";
    public static final String TAG_EVENTVENUE = "event_venue";
    public static final String TAG_EVENTDATE = "event_date";
    public static final String TAG_STARTTIME = "start_time";
    public static final String TAG_ENDTIME = "end_time";
    public static final String TAG_UNIQUEPARTICIPANTS = "unique_participants";
    public static final String TAG_NOTIFIED = "isNotified" ;
    public static final String TAG_NOTIFIED_FOR_SCHEDULE = "isNotifiedForSchedule";
    public static final String TAG_ADDEVENTTITILE = "add_event_title";
    public static final String TAG_SUBEVENT_NAME = "sub_event_name";
    public static final String TAG_SUBEVENT_PARTICIPANTS = "sub_event_participants";
    public static final String TAG_GAME = "game";
    public static final String TAG_TEAM_SIZE = "team_size";
    public static final String TAG_AGE_GROUP = "age_group";
    public static final String TAG_GENDER = "gender";
    public static final String TAG_EDITFLAG = "edit_flag";
    public static final String TAG_EDIT_POSITION = "edit_position";
    public static final String TAG_ADDED_PARTICIPANTS_ARRAY = "added_participants_array";
    public static final String TAG_SE_EDIT_POSITION = "se_edit_pos";
//    public static final String TAG_IMG_POSITION = "img_position";

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

    public static final int EDIT_EVENT_REQ_CODE = 250;
    public static final int EVENT_EDITED = 350;

    public static final String BASE_URL = "http://jvoc-sports-corner-db.16mb.com/";
    public static final String URL_GETALLEVENTS = BASE_URL + "get_all_events.php";

    public static final String TAG_JSON_EVENTS = "events";
    public static final String TAG_JSON_SUCCESS = "success";
    public static final String TAG_JSON_EVENTNAME = "eventName";
    public static final String TAG_JSON_STARTDATE = "startDate";
    public static final String TAG_JSON_ENDDATE = "endDate";
    public static final String TAG_JSON_STARTTIME = "startTime";
    public static final String TAG_JSON_ENDTIME = "endTime";
    public static final String TAG_JSON_VENUE = "venue";
    public static final String TAG_JSON_NOOFPARTICIPANTS = "noOfParticipants";
    public static final String TAG_JSON_ISNOTIFIED = "isNotified";
    public static final String TAG_JSON_ISNOTIFIEDFORSCHEDULE = "isNotifiedforSchedule";


    private static String getMyCurrentDate() {
        Calendar myCalendar = Calendar.getInstance();
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        System.out.println(sdf.format(myCalendar.getTime()));
        return sdf.format(myCalendar.getTime());
    }

    private static String getMyCurrentTime() {
        Calendar myCalendar = Calendar.getInstance();
        int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = myCalendar.get(Calendar.MINUTE);
        int format = myCalendar.get(Calendar.AM_PM);

        String dispHour = String.valueOf(hour), dispMinute = String.valueOf(minute);

        if( hour > 12 )     hour -= 12;
        if (hour < 10)      dispHour = "0" + hour;
        if (minute < 10)    dispMinute = "0" + minute;
        String am_pm = (format == 0) ? "AM" : "PM";
        return dispHour + ":" + dispMinute + " " + am_pm;
    }

    public static String reversedate(String date) {
        String parts[] = date.split("-");
        String year = parts[0];
        String month = parts[1];
        String day = parts[2];
        System.out.println(day + "-" + month + "-" + year);
        return day + "-" + month + "-" + year;
    }


    public static String getSpecificTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar myCalendar = Calendar.getInstance();
        myCalendar.setTime(date);   // assigns calendar to given date
        int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = myCalendar.get(Calendar.MINUTE);
        int format = myCalendar.get(Calendar.AM_PM);

        String dispHour = String.valueOf(hour), dispMinute = String.valueOf(minute);

        if( hour > 12 )     hour -= 12;
        if (hour < 10)      dispHour = "0" + hour;
        if (minute < 10)    dispMinute = "0" + minute;
        String am_pm = (format == 0) ? "AM" : "PM";
        System.out.println(dispHour + ":" + dispMinute + " " + am_pm);
        return dispHour + ":" + dispMinute + " " + am_pm;
    }
}
