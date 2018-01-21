package com.example.user.siriusphotos.utils;

import android.graphics.Bitmap;

/**
 * Created by user on 20.01.2018.
 */

public class RecyclerViewData {
    private Bitmap img;
    private Query type;
    private String nameVis;

    public Bitmap getImg() {
        return img;
    }

    public Query getType() {
        return type;
    }

    public String getNameVis() {
        return nameVis;
    }

    public RecyclerViewData(Bitmap bitmap, String name, Query type) {
        this.type = type;
        img = bitmap;
        nameVis = (name);
    }
}
