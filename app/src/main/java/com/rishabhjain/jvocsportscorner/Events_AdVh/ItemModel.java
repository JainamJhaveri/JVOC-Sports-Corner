package com.rishabhjain.jvocsportscorner.Events_AdVh;

import android.util.Log;

/**
 * Created by jainu on 6/6/16.
 */
public class ItemModel {
    private final String TAG = this.getClass().getSimpleName();
    private final String eventname, venue, date, time, participants;


    public ItemModel(String eventname, String venue, String date, String time, String participants) {
        this.eventname = eventname;
        this.venue = venue;
        this.date = date;
        this.time = time;
        this.participants = participants;
    }

    String getEventname() {
        return eventname;
    }

    String getVenue() {
        return venue;
    }

    String getDate() {
        return date;
    }

    String getTime() {
        return time;
    }

    String getParticipants() {
        return participants;
    }
}
