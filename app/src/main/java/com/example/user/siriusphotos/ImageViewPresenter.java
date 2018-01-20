package com.example.user.siriusphotos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.File;


@InjectViewState
public class ImageViewPresenter extends MvpPresenter<IImageView> {
    private MainPresenter mainPresenter;

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    public void setImage(Bitmap file) {
        getViewState().setImage(file);
    }



    void selectImageFromGallery() {
        mainPresenter.selectImageFromGallery(new MainPresenter.ImageReceiver() {
            @Override
            public void acceptImage(File file) {
                setImage(BitmapFactory.decodeFile(file.getAbsolutePath()));
            }
        });
    }
    void selectImageFromCamera(){
        mainPresenter.selectImageFromCamera(new MainPresenter.ImageReceiver() {
            @Override
            public void acceptImage(File file) {
                setImage(BitmapFactory.decodeFile(file.getAbsolutePath()));
            }
        });
    }
}
