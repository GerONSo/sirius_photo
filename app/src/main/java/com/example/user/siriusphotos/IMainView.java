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
    interface OnCallBack {
        void onCallback(File file);
    }
    @StateStrategyType(SkipStrategy.class)
    void getImageFromGallery(OnCallBack callback);
    @StateStrategyType(SkipStrategy.class)
    void getImageFromCamera(OnCallBack callback);
    @StateStrategyType(SingleStateStrategy.class)
    void startLoadView();
    @StateStrategyType(SingleStateStrategy.class)
    void stopLoadView();
}
