package com.example.user.siriusphotos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.File;

/**
 * Created by shana on 17-Jan-18.
 */

@InjectViewState
public class RecyclerViewPresenter extends MvpPresenter<IRecyclerView> {
    private MainPresenter mainPresenter;

    interface OnCallBack{
        void onCallBack(Bitmap file);
    }

    void selectImageGalerey(final OnCallBack callBack){
        mainPresenter.selectImageFromGalery(new MainPresenter.OnCallBack() {
            @Override
            public void onCallBack(File file) {
                callBack.onCallBack(BitmapFactory.decodeFile(file.getAbsolutePath()));
            }
        });
    }
}
