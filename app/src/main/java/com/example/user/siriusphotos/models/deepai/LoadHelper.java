package com.example.user.siriusphotos.models.deepai;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

    public File saveBitmap(Bitmap img) {
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/photoStyle");
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/photoStyle/" + String.valueOf((int) (Math.random() * 100000000)) + ".jpg");
        try {
            if (!file.isFile())
                file.createNewFile();
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                img.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            } finally {
                if (fos != null) fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}