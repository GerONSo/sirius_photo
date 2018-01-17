package com.example.user.siriusphotos;

import android.graphics.Bitmap;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by shana on 17-Jan-18.
 */

public interface IRecyclerView extends MvpView{
    @StateStrategyType(AddToEndSingleStrategy.class)
    void insertImg(Bitmap img);
}