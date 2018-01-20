package com.example.user.siriusphotos.presenters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.user.siriusphotos.R;
import com.example.user.siriusphotos.utils.Query;
import com.example.user.siriusphotos.utils.RecyclerViewData;
import com.example.user.siriusphotos.views.IRecyclerView;

import java.util.ArrayList;

@InjectViewState
public class RecyclerViewPresenter extends MvpPresenter<IRecyclerView> {
    private MainPresenter mainPresenter;
    private ArrayList<RecyclerViewData> list;
    public void setMainPresenter(MainPresenter mainPresenter, Resources resource) {
        this.mainPresenter = mainPresenter;
        list = new ArrayList<>();
        list.add(new RecyclerViewData(BitmapFactory.decodeResource(resource, R.drawable.colorizer), "colorizer", Query.COLORIZER));
    }
    public void drawList(){
        getViewState().createList(list);
    }
    public void query(RecyclerViewData data){
        mainPresenter.query(data);
    }
}
