package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.AddParticipants_AdVh;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jainu on 10/6/16.
 */
public class ItemModel implements Parcelable{
    private final String name, mem_no, gender, bdate;
    private boolean isSelected;

    public ItemModel(String name, String mem_no, String gender, String bdate) {
        this.name = name;
        this.mem_no = mem_no;
        this.gender = gender;
        this.bdate = bdate;
    }

    public ItemModel(String name, String mem_no, String gender, String bdate, boolean isSelected) {
        this.name = name;
        this.mem_no = mem_no;
        this.gender = gender;
        this.bdate = bdate;
        this.isSelected = isSelected;
    }

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

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    protected ItemModel(Parcel in) {
        name = in.readString();
        mem_no = in.readString();
        gender = in.readString();
        bdate = in.readString();
    }

    public static final Parcelable.Creator<ItemModel> CREATOR = new Parcelable.Creator<ItemModel>() {
        @Override
        public ItemModel createFromParcel(Parcel in) {
            return new ItemModel(in);
        }

        @Override
        public ItemModel[] newArray(int size) {
            return new ItemModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(mem_no);
        dest.writeString(gender);
        dest.writeString(bdate);
    }
}
