package com.rishabhjain.jvocsportscorner.Media_AdVh;

import android.graphics.drawable.Drawable;

/**
 * Created by jainu on 10/6/16.
 */
public class ItemModel {
    private final String name, date;
    private final Drawable cover_image;

    public ItemModel(String name, String date, Drawable cover_image) {
        this.name = name;
        this.date = date;
        this.cover_image = cover_image;
    }

    String getName() {
        return name;
    }

    String getDate() {
        return date;
    }

    Drawable getCover_image() {
        return cover_image;
    }
}
