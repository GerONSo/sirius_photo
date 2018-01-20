package com.example.user.siriusphotos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.File;


@InjectViewState
public class ImageViewPresenter extends MvpPresenter<IImageView> {
    private MainPresenter mainPresenter;
    private FloatingActionButton btn;

    public void setBtn(FloatingActionButton btn) {
        this.btn = btn;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImageFromCamera();
            }
        });
    }

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    public void setImg(Bitmap file) {
        getViewState().setImage(file);
    }

    void selectImageFromGallery() {

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
