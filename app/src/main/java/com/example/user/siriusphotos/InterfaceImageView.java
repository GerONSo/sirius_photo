package com.example.user.siriusphotos;

import android.graphics.Bitmap;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by user on 17.01.2018.
 */

public interface InterfaceImageView extends MvpView {
    @StateStrategyType(SingleStateStrategy.class)
    void setImage(Bitmap img);
}