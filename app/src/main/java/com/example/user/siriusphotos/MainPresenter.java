package com.example.user.siriusphotos;

import com.arellomobile.mvp.MvpPresenter;

import java.io.File;

/**
 * Created by shana on 17-Jan-18.
 */

public class MainPresenter extends MvpPresenter<IMainView> {
    interface OnCallBack {
        void onCallBack(File file);
    }
    void selectImageFromGalery(final OnCallBack callBack) {
        getViewState().getImageFromGallery(new IMainView.OnCallBack() {
            @Override
            public void onCallback(File file) {
                callBack.onCallBack(file);
            }
        });

    }
    void selectIageFromCamera(final OnCallBack callBack){
        getViewState().getImageFromCamera(new IMainView.OnCallBack() {
            @Override
            public void onCallback(File file) {
                callBack.onCallBack(file);
            }
        });
    }
}
