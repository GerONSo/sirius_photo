package com.example.user.siriusphotos.views;

import android.graphics.Bitmap;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.user.siriusphotos.utils.RecyclerViewData;

import java.util.ArrayList;

public interface IRecyclerView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void insertImg(Bitmap img);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void createList(ArrayList<RecyclerViewData> list);
    @StateStrategyType(SkipStrategy.class)
    void update();
}
