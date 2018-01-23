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
        list.add(getBitmap(R.drawable.add, "Own Effect", Query.DEVELOPMENT));
        list.add(getBitmap(R.drawable.colorizer, "Colorizer", Query.COLORIZER));
        list.add(getBitmap(R.drawable.deep_dream, "Deep Dream", Query.DEEP_DREAM));
        list.add(getBitmap(R.drawable.la_muse, "La Muse", Query.LA_MUSE));
        list.add(getBitmap(R.drawable.wave, "Wave", Query.WAVE));
        list.add(getBitmap(R.drawable.rain_princess, "Rain Princess", Query.RAIN_PRINCESS));
        list.add(getBitmap(R.drawable.the_scream, "The Scream", Query.THE_SCREAM));
        list.add(getBitmap(R.drawable.the_starry_night_locked,"Starry Night",Query.DEVELOPMENT));
        list.add(getBitmap(R.drawable.water_lilies_locked,"Water Lilies",Query.DEVELOPMENT));
        list.add(getBitmap(R.drawable.dogs_playing_poker_locked,"Poker Dogs",Query.DEVELOPMENT));

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


    private void setListClone() {
        listClone = new ArrayList<>();
        for (RecyclerViewData data : list) {
            listClone.add(data.getImg());
        }
    }

}
