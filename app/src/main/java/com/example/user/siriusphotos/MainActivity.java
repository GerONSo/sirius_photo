package com.example.user.siriusphotos;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.io.File;
import java.util.List;

public class MainActivity extends MvpAppCompatActivity implements IMainView {

    @InjectPresenter
    MainPresenter presenter;

    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_GALLERY = 2;

    public static final String RECYCLER_VIEW_FRAGMENT_TAG = "recycler view";
    public static final String IMAGE_VIEW_FRAGMENT_TAG = "image view";

    private  RecyclerViewFragment fragment;
    private ImageViewFragment imageViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton photoBtn = findViewById(R.id.camera_button);
        fragment = RecyclerViewFragment.newInstance();
        imageViewFragment = ImageViewFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.image_frame, imageViewFragment, IMAGE_VIEW_FRAGMENT_TAG)
                .replace(R.id.recycler_frame, fragment, RECYCLER_VIEW_FRAGMENT_TAG)
                .commit();
    }

    @Override
    public void getTempFilesDir(File dir) {
        dir = getCacheDir();
    }

    @Override
    public void requestImageFromGallery() {

    }

    @Override
    public void requestImageFromCamera(File file) {
        Uri contentUri = FileUtils.getContentUri(getApplicationContext(), file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                .putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        List<ResolveInfo> cameraActivities = getPackageManager()
                .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo activity : cameraActivities) {
            grantUriPermission(activity.activityInfo.packageName,
                    contentUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        imageViewFragment.presenter.setMainPresenter(presenter);
        fragment.presenter.setMainPresenter(presenter);
        imageViewFragment.setListnerButton();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CAMERA:
                    presenter.onImageReadyFromCamera();
                    break;
            }
        }
    }

    @Override
    public void startLoadView() {

    }

    @Override
    public void stopLoadView() {

    }

}
