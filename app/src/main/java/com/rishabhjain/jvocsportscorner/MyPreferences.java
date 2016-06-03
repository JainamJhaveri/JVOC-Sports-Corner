package com.rishabhjain.jvocsportscorner;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jainu on 3/6/16.
 */
public class MyPreferences {
    private static final String DATA = "data";

    public MyPreferences() {}

    public static void setSPTitle(Context context, String title){
        SharedPreferences sharedPref = context.getSharedPreferences(DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putString("title", title);
        prefEditor.commit();
    }

    public static String getSPTitle(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATA, Context.MODE_PRIVATE);
        return sharedPreferences.getString("title", "Default title");
    }

}
