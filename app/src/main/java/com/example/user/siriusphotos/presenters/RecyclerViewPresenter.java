package com.example.user.siriusphotos.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.user.siriusphotos.views.IRecyclerView;

@InjectViewState
public class RecyclerViewPresenter extends MvpPresenter<IRecyclerView> {
    private MainPresenter mainPresenter;

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }
}
