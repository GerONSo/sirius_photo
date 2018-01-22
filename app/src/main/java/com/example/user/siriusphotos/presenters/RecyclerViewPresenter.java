package com.example.user.siriusphotos.presenters;

import android.content.res.Resources;
import android.graphics.Bitmap;
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
    private ArrayList<Bitmap> listClone;

    public Resources getResources() {
        return resources;
    }

    private Resources resources;

    public void setMainPresenter(MainPresenter mainPresenter, Resources resource) {
        this.mainPresenter = mainPresenter;
        this.resources = resource;
        mainPresenter.setRecyclerPresenter(this);
        list = new ArrayList<>();
        list.add(getBitmap(R.drawable.add, "Own Effect", Query.ADD_PHOTO_FROM_GALLERY));
        list.add(getBitmap(R.drawable.colorizer, "Colorizer", Query.COLORIZER));
        list.add(getBitmap(R.drawable.the_starry_night, "Starry night", Query.STARRY_NIGHT));
        list.add(getBitmap(R.drawable.the_scream, "The Scream", Query.THE_SCREAM));
        list.add(getBitmap(R.drawable.water_lilies, "Water Lilies", Query.WATER_LILIES));
        list.add(getBitmap(R.drawable.dogs_playing_poker, "Poker Dogs", Query.DOGS_PLAYING_POKER));
        setListClone();
    }

    public File getImg() {
        return list.get(0).getFile();
    }

    public void drawList() {
        getViewState().createList(list);
    }

    public void query(RecyclerViewData data) {
        mainPresenter.query(data);
    }

    private RecyclerViewData getBitmap(int id, String name, Query query) {
        return new RecyclerViewData(BitmapFactory.decodeResource(resources, id), name, query);
    }

    public void setList() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setImg(listClone.get(i));
        }
    }


    public void setListClone() {
        listClone = new ArrayList<>();
        for (RecyclerViewData data : list) {
            listClone.add(data.getImg());
        }
    }

}
