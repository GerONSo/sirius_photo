package com.example.user.siriusphotos;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.io.File;

public interface IMainView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void getTempFilesDir(Box<File> dir);
    @StateStrategyType(SkipStrategy.class)
    void requestImageFromGallery();
    @StateStrategyType(SkipStrategy.class)
    void requestImageFromCamera(File file);
    @StateStrategyType(SingleStateStrategy.class)
    void startLoadView();
    @StateStrategyType(SingleStateStrategy.class)
    void stopLoadView();
    @StateStrategyType(OneExecutionStateStrategy.class)
    void createFragment();
}
