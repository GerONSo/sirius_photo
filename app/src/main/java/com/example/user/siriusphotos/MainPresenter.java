package com.example.user.siriusphotos;

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

    void selectImageFromGalery(ImageReceiver callback) {
        this.callback = callback;

    }
    void selectImageFromCamera(ImageReceiver callback) {
        this.callback = callback;
        file = getTempPhotoFile();
        getViewState().requestImageFromCamera(file);
    }

    void onImageReadyFromCamera() {
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
