package com.example.user.siriusphotos.models.deepai;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by user on 21.01.2018.
 */

public class MyTarget implements Target {
    interface OnLoad{
        void onLoad(File file);
        void onFaile();
    }
    private OnLoad call;
    private String url;
    public MyTarget(String url, OnLoad call) {
        this.call = call;
        this.url = url;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

        Log.d("onBitmapLoaded", "Ok");

        String s[] = url.split("/");
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + s[s.length - 1]);
        try {
            if (!file.isFile())
                file.createNewFile();
            FileOutputStream ostream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 95, ostream);
            ostream.flush();
            ostream.close();
            call.onLoad(file);
        } catch (IOException e) {
            Log.e("IOException", e.getLocalizedMessage());
        }
    }


    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
         Log.d("m", "FAil");
         call.onFaile();
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
        Log.d("m", url);
    }
}
