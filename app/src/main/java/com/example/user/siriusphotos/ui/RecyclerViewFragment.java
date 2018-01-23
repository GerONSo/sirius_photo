package com.example.user.siriusphotos.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.user.siriusphotos.R;
import com.example.user.siriusphotos.presenters.MainPresenter;
import com.example.user.siriusphotos.presenters.RecyclerViewPresenter;
import com.example.user.siriusphotos.utils.RecyclerViewData;
import com.example.user.siriusphotos.views.IRecyclerView;

import java.util.ArrayList;

public class RecyclerViewFragment extends MvpAppCompatFragment implements IRecyclerView {

    @InjectPresenter
    RecyclerViewPresenter presenter;
    private PictureAdapter adapter;
    private MainPresenter mainPresenter;
    private ImageView img;

    public ArrayList<RecyclerViewData> getList(){
        return presenter.getList();
    }

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    private RecyclerView listView;
    public RecyclerViewFragment() {}
    public static RecyclerViewFragment newInstance(){
        return new RecyclerViewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null)
            presenter.setMainPresenter(mainPresenter, getResources());
        listView = view.findViewById(R.id.listView);
        presenter.drawList();
    }

    @Override
    public void insertImg(Bitmap img) {

    }

    @Override
    public void createList(ArrayList<RecyclerViewData> list) {
        listView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new PictureAdapter(this, new PictureAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(RecyclerViewData data) {
                presenter.query(data);
            }
        });
        listView.setAdapter(adapter);
    }
    @Override
    public void update(){
        adapter.notifyDataSetChanged();
    }
}
