package com.example.user.siriusphotos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ImageViewFragment extends Fragment {

    private ImageView imageView;
    private FloatingActionButton photoBtn;

    public ImageViewFragment() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_image_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        imageView=view.findViewById(R.id.image_view);
        photoBtn=view.findViewById(R.id.camera_button);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.elbi);
        Log.d("mylog",bitmap.toString());
        imageView.setImageBitmap(bitmap);
    }
}
