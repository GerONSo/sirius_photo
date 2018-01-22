package com.example.user.siriusphotos.models.deepai;

import android.content.Context;

import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by user on 21.01.2018.
 */

public final class LoadHelper {

    private static LoadHelper loadHelper;
    private MyTarget target;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    private LoadHelper() {
    }

    public static LoadHelper getInstance() {
        if (loadHelper == null)
            loadHelper = new LoadHelper();


        return loadHelper;
    }

    public interface OnLoad {
        void onLoad(File file);
        void onFaile();
    }

    //save image
    public void imageDownload(String url, final OnLoad call) {
        Picasso picasso = Picasso.with(context);
        picasso.setLoggingEnabled(true);
        target = new MyTarget(url, new MyTarget.OnLoad() {
            @Override
            public void onLoad(File file) {
                call.onLoad(file);
            }

            @Override
            public void onFaile() {
                call.onFaile();
            }
        });
        picasso.load(url).into(target);
    }

}