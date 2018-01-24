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


    public File retro(Bitmap original){
        Bitmap result = Bitmap.createBitmap(original.getWidth(),
                original.getHeight(),
                Bitmap.Config.ARGB_8888);

        ColorMatrix matrix = new ColorMatrix(new float[]{
                0.6279345635605994f, 0.3202183420819367f, -0.03965408211312453f, 0, 9.651285835294123f,
                0.02578397704808868f, 0.6441188644374771f, 0.03259127616149294f, 0, 7.462829176470591f,
                0.0466055556782719f, -0.0851232987247891f, 0.5241648018700465f, 0, 5.159190588235296f,
                0, 0, 0, 1, 0});


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

    public File upColor(Bitmap original){
        Bitmap result = Bitmap.createBitmap(original.getWidth(),
                original.getHeight(),
                Bitmap.Config.ARGB_8888);

        ColorMatrix matrix = new ColorMatrix(new float[]{
                1.1285582396593525f, -0.3967382283601348f, -0.03992559172921793f, 0, 63.72958762196502f,
                -0.16404339962244616f, 1.0835251566291304f, -0.05498805115633132f, 0, 24.732407896706203f,
                -0.16786010706155763f, -0.5603416277695248f, 1.6014850761964943f, 0, 35.62982807460946f,
                0, 0, 0, 1, 0});


        Canvas canvas = new Canvas(result);

        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(matrix));

        canvas.drawBitmap(original,0,0,paint);

        return LoadHelper.getInstance().saveBitmap(result);
    }
}
