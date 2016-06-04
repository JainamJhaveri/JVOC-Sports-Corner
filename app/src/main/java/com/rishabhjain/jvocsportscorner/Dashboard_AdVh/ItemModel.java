package com.rishabhjain.jvocsportscorner.Dashboard_AdVh;

import android.graphics.drawable.Drawable;

/**
 * Created by jainu on 4/6/16.
 */
public class ItemModel {
    private final String name;
    private final Drawable picture;

    public ItemModel(String name, Drawable picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public Drawable getPicture() {
        return picture;
    }
}
