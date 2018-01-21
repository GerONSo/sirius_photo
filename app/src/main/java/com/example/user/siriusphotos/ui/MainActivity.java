package com.example.user.siriusphotos.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.user.siriusphotos.utils.Box;
import com.example.user.siriusphotos.views.IMainView;
import com.example.user.siriusphotos.presenters.MainPresenter;
import com.example.user.siriusphotos.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class MainActivity extends MvpAppCompatActivity implements IMainView {

    @InjectPresenter
    MainPresenter presenter;

    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_GALLERY = 2;

    public static final String RECYCLER_VIEW_FRAGMENT_TAG = "recycler view";
    public static final String IMAGE_VIEW_FRAGMENT_TAG = "image view";

    private static final String CONTENT_AUTHORITY = "com.example.user.siriusphotos.fileprovider";

    private RecyclerViewFragment fragment;
    private ImageViewFragment imageViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            presenter.createFragment();
        }
    }

    @Override
    public void getTempFilesDir(Box<File> dir) {
        dir.setValue(getFilesDir());
    }

    @Override
    public void createFileByContentUri(Uri src, File dst) {
        try (InputStream in = getContentResolver().openInputStream(src)) {
            try (OutputStream out = new FileOutputStream(dst)) {
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }
        } catch (IOException e) {
            Toast.makeText(this, R.string.error_filesystem, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void requestImageFromGallery() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                .setType("image/*");
        startActivityForResult(pickIntent, REQUEST_GALLERY);
    }

    @Override
    public void requestImageFromCamera(File file) {
        Uri contentUri = FileProvider.getUriForFile(this, CONTENT_AUTHORITY, file);;
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
                case REQUEST_GALLERY:
                    presenter.onImageReadyFromGallery(data.getData());
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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        createInstagramIntent("image/*",getPath());
        return true;
    }
    String getPath(){
        String uri="";
        return uri;
    }


    private void createInstagramIntent(String type, String mediaPath){

        // Create the new Intent using the 'Send' action.
        Intent share = new Intent(Intent.ACTION_SEND);

        // Set the MIME type
        share.setType(type);

        // Create the URI from the media
        File media = new File(mediaPath);
        Uri uri = Uri.fromFile(media);

        // Add the URI to the Intent.
        share.putExtra(Intent.EXTRA_STREAM, uri);

        // Broadcast the Intent.
        startActivity(Intent.createChooser(share, "Share to"));
    }

}
