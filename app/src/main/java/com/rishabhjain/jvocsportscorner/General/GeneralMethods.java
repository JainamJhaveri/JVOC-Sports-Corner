package com.rishabhjain.jvocsportscorner.General;

import android.support.v7.widget.RecyclerView;

public class GeneralMethods {
    public static int get_rv_adapter_count(RecyclerView rv) {
        return rv.getAdapter().getItemCount();
    }
}
