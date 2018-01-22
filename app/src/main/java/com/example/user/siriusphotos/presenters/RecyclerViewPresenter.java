package com.example.user.siriusphotos.presenters;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.user.siriusphotos.R;
import com.example.user.siriusphotos.utils.Query;
import com.example.user.siriusphotos.utils.RecyclerViewData;
import com.example.user.siriusphotos.views.IRecyclerView;

import java.io.File;
import java.util.ArrayList;

@InjectViewState
public class RecyclerViewPresenter extends MvpPresenter<IRecyclerView> {
    private MainPresenter mainPresenter;
    private ArrayList<RecyclerViewData> list;
    public void setMainPresenter(MainPresenter mainPresenter, Resources resource) {
        this.mainPresenter = mainPresenter;
        mainPresenter.setRecyclerPresenter(this);
        list = new ArrayList<>();
        list.add(new RecyclerViewData(BitmapFactory.decodeResource(resource, R.drawable.colorizer), "add", Query.ADDPHOTOFORGALLEREY));
    }
    public void setImg(File file){
        list.get(0).setImg(BitmapFactory.decodeFile(file.getAbsolutePath()));
        list.get(0).setFile(file);
        drawList();
    }
    public File getImg(){
        return list.get(0).getFile();
    }
    public void drawList(){
        getViewState().createList(list);
    }
    public void query(RecyclerViewData data){
        mainPresenter.query(data);
    }
}
