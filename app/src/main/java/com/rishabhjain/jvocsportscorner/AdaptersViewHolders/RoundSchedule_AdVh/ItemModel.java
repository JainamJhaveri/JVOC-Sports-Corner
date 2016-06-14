package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.RoundSchedule_AdVh;

public class ItemModel {
    String game_no, p1_name, p2_name, p1_mem_no, p2_mem_no;
    boolean p1_wins;

    public ItemModel(String game_no, String p1_name, String p2_name, String p1_mem_no, String p2_mem_no) {
        this.game_no = game_no;
        this.p1_name = p1_name;
        this.p2_name = p2_name;
        this.p1_mem_no = p1_mem_no;
        this.p2_mem_no = p2_mem_no;
//        this.p1_wins = p1_wins;
    }

    public String getGame_no() {
        return game_no;
    }

    public String getP1_name() {
        return p1_name;
    }

    public String getP2_name() {
        return p2_name;
    }

    public String getP1_mem_no() {
        return p1_mem_no;
    }

    public String getP2_mem_no() {
        return p2_mem_no;
    }

    public boolean isP1_wins() {
        return p1_wins;
    }
}
