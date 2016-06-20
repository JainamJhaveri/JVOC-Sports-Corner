package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.SEParticipants_AdVh;

public class ItemModel {
    private final String name, mem_no, gender, bdate;

    public String getName() {
        return name;
    }

    public String getMem_no() {
        return mem_no;
    }

    public String getGender() {
        return gender;
    }

    public String getBdate() {
        return bdate;
    }

    public ItemModel(String name, String mem_no, String gender, String bdate) {
        this.name = name;
        this.mem_no = mem_no;
        this.gender = gender;
        this.bdate = bdate;
    }
}
