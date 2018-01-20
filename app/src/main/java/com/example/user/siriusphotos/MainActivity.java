package com.example.user.siriusphotos;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;

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

    private RecyclerViewFragment fragment;
    private ImageViewFragment imageViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            presenter.createFragment();
        }
    }

    @Override
    public void getTempFilesDir(Box<File> dir) {
        dir.setValue(getFilesDir());
    }

    @Override
    public void requestImageFromGallery() {

    }

    @Override
    public void requestImageFromCamera(File file) {
        Uri contentUri = FileUtils.getContentUri(this, file);
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

    @Override
    public void createFragment() {
        FloatingActionButton photoBtn = findViewById(R.id.camera_button);
        fragment = RecyclerViewFragment.newInstance();
        imageViewFragment = ImageViewFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.image_frame, imageViewFragment, IMAGE_VIEW_FRAGMENT_TAG)
                .add(R.id.recycler_frame, fragment, RECYCLER_VIEW_FRAGMENT_TAG)
                .commit();
        imageViewFragment.setMainPresenter(presenter);
        fragment.setMainPresenter(presenter);

    }

}
