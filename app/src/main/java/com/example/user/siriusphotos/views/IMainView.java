package com.example.user.siriusphotos.views;

import android.net.Uri;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.user.siriusphotos.utils.Box;

import java.io.File;

public interface IMainView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void getTempFilesDir(Box<File> dir);
    @StateStrategyType(OneExecutionStateStrategy.class)
    void createFileByContentUri(Uri src, File dst);
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
