package com.example.user.siriusphotos.utils;

import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by user on 20.01.2018.
 */

public class RecyclerViewData {
    private Bitmap img;
    private Query type;
    private String nameVis;
    private File file;

    public void setFile(File file) {
        this.file = file;
    }

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

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
