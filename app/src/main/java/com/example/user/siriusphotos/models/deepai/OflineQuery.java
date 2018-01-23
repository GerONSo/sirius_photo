package com.example.user.siriusphotos.models.deepai;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import java.io.File;

/**
 * Created by user on 23.01.2018.
 */

public final class OflineQuery {
    private static OflineQuery instance;

    private OflineQuery(){}

    public static OflineQuery getInstance(){
        if(instance == null)
            instance = new OflineQuery();
        return instance;
    }
    public File toGrayscale(Bitmap original) {
        Bitmap result = Bitmap.createBitmap(original.getWidth(),
                original.getHeight(),
                Bitmap.Config.ARGB_8888);
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));
        canvas.drawBitmap(original,0,0,paint);
        return LoadHelper.getInstance().saveBitmap(result);
    }

    public File toInvert(Bitmap original){
        Bitmap result = Bitmap.createBitmap(original.getWidth(),
                original.getHeight(),
                Bitmap.Config.ARGB_8888);

        ColorMatrix matrix = new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0
        });

        Canvas canvas = new Canvas(result);

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));

        canvas.drawBitmap(original,0,0,paint);

        return LoadHelper.getInstance().saveBitmap(result);
    }
}
