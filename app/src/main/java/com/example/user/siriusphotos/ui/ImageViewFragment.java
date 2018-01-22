package com.example.user.siriusphotos.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.user.siriusphotos.views.IImageView;
import com.example.user.siriusphotos.presenters.ImageViewPresenter;
import com.example.user.siriusphotos.presenters.MainPresenter;
import com.example.user.siriusphotos.R;

import java.io.File;

public class ImageViewFragment extends MvpAppCompatFragment implements IImageView {

    private ImageView imageView;
    private FloatingActionButton photoBtn;
    private ProgressBar progressBar;
    @InjectPresenter
    ImageViewPresenter presenter;

    private MainPresenter mainPresenter;

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    @Override
    public void setImage(Bitmap img) {
        imageView.setImageBitmap(img);
    }

    @Override
    public void getViewSize(Point size) {
        size.set(imageView.getWidth(), imageView.getHeight());
    }

    @Override
    public void startLoad() {
        imageView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishLoad() {
        progressBar.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
    }


    public static ImageViewFragment newInstance() {
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
        if (savedInstanceState == null) {
            presenter.setMainPresenter(mainPresenter);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.open_gallery);
            Log.d("mylog", bitmap.toString());
            presenter.setImage(bitmap);
        }
        imageView = view.findViewById(R.id.image_view);
        progressBar = view.findViewById(R.id.progress_bar);
        photoBtn = view.findViewById(R.id.camera_button);
        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.selectImageFromCamera();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.selectImageFromGallery();
            }
        });
    }
}
