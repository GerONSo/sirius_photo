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
public class ImageViewPresenter extends MvpPresenter<IImageView> {
    private MainPresenter mainPresenter;

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    void selectImageFromGallery() {
        mainPresenter.selectImageFromGalery(new MainPresenter.OnCallBackGalleryMainPresenter() {
            @Override
            public void onCallBackGallery(File file) {
                getViewState().setImage(BitmapFactory.decodeFile(file.getAbsolutePath()));
            }
        });
    }

    void setImage(File file) {
        getViewState().setImage(BitmapFactory.decodeFile(file.getAbsolutePath()));
    }
}
