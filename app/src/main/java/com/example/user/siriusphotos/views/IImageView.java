package com.example.user.siriusphotos.views;

import android.graphics.Bitmap;
import android.graphics.Point;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.io.File;

public interface IImageView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setImage(Bitmap img);
    @StateStrategyType(SkipStrategy.class)
    void getViewSize(Point size);
}
