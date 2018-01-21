package com.example.user.siriusphotos.models.deepai;

import android.content.Context;
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

public final class LoadHelper {

    private static LoadHelper loadHelper;

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
    }

    //save image
    public void imageDownload(String url, OnLoad call) {

        Picasso.with(context)
                .load("https://api.deepai.org/media/results/669629/fdd7ba80-b460-4143-aa21-20a7b0413a24/result76649848.jpg")
                .into(getTarget(url, call));
    }

    private static Target getTarget(final String url, final OnLoad call) {
        Target target = new Target() {

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.d("onBitmapLoaded", "Ok");


                        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/save.jpg");
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
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.d("m", "onPrepare");
            }
        };
        return target;
    }
}