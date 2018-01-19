package com.example.user.siriusphotos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {

    @InjectPresenter
    MainPresenter presenter;

    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_GALLERY = 2;

    public static final String RECYCLER_VIEW_FRAGMENT_TAG="recycler view";
    public static final String IMAGE_VIEW_FRAGMENT_TAG="image view";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar=findViewById(R.id.toolbar);
       //setSupportActionBar(toolbar);
        RecyclerViewFragment fragment=RecyclerViewFragment.newInstance();
        ImageViewFragment imageViewFragment=ImageViewFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.image_frame,imageViewFragment,IMAGE_VIEW_FRAGMENT_TAG)
                .replace(R.id.recycler_frame,fragment,RECYCLER_VIEW_FRAGMENT_TAG)
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
