package com.example.user.siriusphotos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

public class ImageViewFragment extends MvpAppCompatFragment implements IImageView{
    private ImageView imageView;
    private FloatingActionButton photoBtn;

    @InjectPresenter
    ImageViewPresenter presenter;


    @Override
    public void setImage(Bitmap img) {
        imageView.setImageBitmap(img);
    }

    public static ImageViewFragment newInstance(){
        return new ImageViewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        imageView=view.findViewById(R.id.image_view);
        photoBtn=view.findViewById(R.id.camera_button);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.dogs_playing_poker);
        Log.d("mylog",bitmap.toString());
        imageView.setImageBitmap(bitmap);

    }
    public void setListnerButton(){
        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.selectImageFromCamera();
            }
        });
    }
}
