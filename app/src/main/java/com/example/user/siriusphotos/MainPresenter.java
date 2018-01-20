package com.example.user.siriusphotos;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.File;

@InjectViewState
public class MainPresenter extends MvpPresenter<IMainView> {

    public interface ImageReceiver {
        void acceptImage(File file);
    }

    private ImageReceiver callback;
    private File file;

    void selectImageFromGallery(ImageReceiver callback) {
        this.callback = callback;
        file = getTempPhotoFile();
        getViewState().requestImageFromGallery();

    }
    void selectImageFromCamera(ImageReceiver callback) {
        this.callback = callback;
        file = getTempPhotoFile();
        getViewState().requestImageFromCamera(file);
    }

    void onImageReadyFromCamera() {
        callback.acceptImage(file);
    }

    void onImageReadyFromGallery(Uri contentUri) {
        getViewState().createFileByContentUri(contentUri, file);
        callback.acceptImage(file);
    }

    private File getTempPhotoFile() {
        Box<File> dir = new Box<>();
        getViewState().getTempFilesDir(dir);
        return FileUtils.getNewImageFile(dir.getValue(), "tmp_", ".jpg");
    }

    void createFragment(){
        getViewState().createFragment();
    }

}
