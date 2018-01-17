package com.example.user.siriusphotos;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.io.File;

public class MainActivity extends MvpAppCompatActivity implements IMainView{

    @InjectPresenter
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void getImageFromGallery(OnCallBack callback) {
        //здесь код для достования вызова галереи и получения файла
        File file = null;
        callback.onCallback(file);
    }

    @Override
    public void getImageFromCamera(OnCallBack callback) {
        //Здесь код для вызова камеры и получения файла
        File file = null;
        callback.onCallback(file);
    }

    @Override
    public void startLoadView() {

    }

    @Override
    public void stopLoadView() {

    }
}
