package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.Events_AdVh;

public class ItemModel {
    private final String eventname, venue, date, time, participants, isNotified, isNotifiedForSchedule;

    public ItemModel(String eventname, String venue, String date, String time,
                     String participants, String isNotified, String isNotifiedForSchedule) {
        this.eventname = eventname;
        this.venue = venue;
        this.date = date;
        this.time = time;
        this.participants = participants;
        this.isNotified = isNotified;
        this.isNotifiedForSchedule = isNotifiedForSchedule;
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

    String getIsNotified() {
        return isNotified;
    }

    String getIsNotifiedForSchedule() {
        return isNotifiedForSchedule;
    }
}
