package com.example.user.siriusphotos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class RecyclerViewFragment extends MvpAppCompatFragment implements IRecyclerView{

    @InjectPresenter
    RecyclerViewPresenter presenter;

    private RecyclerView listView;
    public RecyclerViewFragment() {
        // Required empty public constructor
    }
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
        listView=view.findViewById(R.id.listView);
        listView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        Bitmap stylizeBitmap=takeBitmap(R.drawable.stylize);
        Bitmap colorizerBitmap=takeBitmap(R.drawable.colorizer);
        Bitmap theScreamBitmap=takeBitmap(R.drawable.the_scream);
        Bitmap theStarryNightBitmap=takeBitmap(R.drawable.the_starry_night);
        Bitmap waterLilies=takeBitmap(R.drawable.water_lilies);
        Bitmap dogsPlayingPokerBitmap=takeBitmap(R.drawable.dogs_playing_poker);
        Bitmap[] dataset=new Bitmap[]{colorizerBitmap,stylizeBitmap,theScreamBitmap,theStarryNightBitmap,waterLilies,dogsPlayingPokerBitmap};
        PictureAdapter adapter=new PictureAdapter(dataset);
        listView.setAdapter(adapter);
    }
    private Bitmap takeBitmap(int id){
        return BitmapFactory.decodeResource(getResources(),id);
    }

    @Override
    public void insertImg(Bitmap img) {

    }
}
