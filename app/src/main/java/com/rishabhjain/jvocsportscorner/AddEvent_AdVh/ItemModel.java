package com.rishabhjain.jvocsportscorner.AddEvent_AdVh;

public class ItemModel {
    private final String sub_event_name, no_of_participants;

    public ItemModel(String sub_event_name, String no_of_participants) {
        this.sub_event_name = sub_event_name;
        this.no_of_participants = no_of_participants;
    }

    String getSub_event_name() {
        return sub_event_name;
    }

    String getNo_of_participants() {
        return no_of_participants;
    }

}
