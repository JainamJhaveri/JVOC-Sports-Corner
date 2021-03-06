package com.rishabhjain.jvocsportscorner.AdaptersViewHolders.Dashboard_AdVh;

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

    String getName() {
        return name;
    }

    Drawable getPicture() {
        return picture;
    }
}
