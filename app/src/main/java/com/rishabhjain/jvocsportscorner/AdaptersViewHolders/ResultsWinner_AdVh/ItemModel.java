package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.ResultsWinner_AdVh;

public class ItemModel {
    // subevent name, winner name, runner's up name, winner's mem no, runner's up mem no
    private final String se_name, wName,rName, wMemNo, rMemNo;

    public ItemModel(String se_name, String wName, String rName, String wMemNo, String rMemNo) {
        this.se_name = se_name;
        this.wName = wName;
        this.rName = rName;
        this.wMemNo = wMemNo;
        this.rMemNo = rMemNo;
    }

    String getSe_name() {
        return se_name;
    }

    String getwName() {
        return wName;
    }

    String getrName() {
        return rName;
    }

    String getwMemNo() {
        return wMemNo;
    }

    String getrMemNo() {
        return rMemNo;
    }
}
