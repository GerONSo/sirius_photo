package com.example.user.siriusphotos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.File;


@InjectViewState
public class ImageViewPresenter extends MvpPresenter<IImageView> {
    private MainPresenter mainPresenter;

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    public void setImg(Bitmap file) {
        getViewState().setImage(file);
    }

    void selectImageFromGallery() {
        Log.d("Ok", "OK");
    }
    void selectImageFromCamera(){
        mainPresenter.selectImageFromCamera(new MainPresenter.ImageReceiver() {
            @Override
            public void acceptImage(File file) {
                setImg((BitmapFactory.decodeFile(file.getAbsolutePath())));
            }
        });
    }
}
