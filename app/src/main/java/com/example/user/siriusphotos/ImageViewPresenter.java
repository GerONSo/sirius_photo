package com.example.user.siriusphotos;

import android.graphics.Bitmap;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.File;

/**
 * Created by shana on 17-Jan-18.
 */

@InjectViewState
public class ImageViewPresenter extends MvpPresenter<IImageView> {
    void selectImageFromGallery() {}
    void setImage(File file) {}
}
