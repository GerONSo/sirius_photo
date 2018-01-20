package com.example.user.siriusphotos;

import android.graphics.BitmapFactory;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.File;


@InjectViewState
public class ImageViewPresenter extends MvpPresenter<IImageView> {
    private MainPresenter mainPresenter;
    private File imgFile;

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    public void setImg(File file) {
        imgFile = file;
        Log.d("mytag", imgFile.getAbsolutePath());
        getViewState().setImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
    }

    void selectImageFromGallery() {

    }
    void selectImageFromCamera(){
        mainPresenter.selectImageFromCamera(new MainPresenter.ImageReceiver() {
            @Override
            public void acceptImage(File file) {
                setImg(file);
            }
        });
    }
}
