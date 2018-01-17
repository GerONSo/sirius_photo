package com.example.user.siriusphotos;

import com.arellomobile.mvp.MvpPresenter;

import java.io.File;

/**
 * Created by shana on 17-Jan-18.
 */

public class MainPresenter extends MvpPresenter<IMainView> {
    interface OnCallBackGalleryMainPresenter{
        void onCallBackGallery(File file);
    }
    void selectImageFromGalery(final OnCallBackGalleryMainPresenter callBack) {
        getViewState().getImageFromGallery(new IMainView.OnCallBack() {
            @Override
            public void onCallback(File file) {
                callBack.onCallBackGallery(file);
            }
        });

    }
}
