package com.example.user.siriusphotos;

import android.graphics.Bitmap;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.io.File;

public interface IImageView extends MvpView {
    @StateStrategyType(SingleStateStrategy.class)
    void setImage(Bitmap img);
}
