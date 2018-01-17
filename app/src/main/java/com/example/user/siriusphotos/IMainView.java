package com.example.user.siriusphotos;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.io.File;

/**
 * Created by user on 17.01.2018.
 */

public interface IMainView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    File getImageFromGallery();
    @StateStrategyType(SkipStrategy.class)
    File getImageFromCamera();
    @StateStrategyType(SingleStateStrategy.class)
    void startLoadView();
    @StateStrategyType(SingleStateStrategy.class)
    void stopLoadView();
}
