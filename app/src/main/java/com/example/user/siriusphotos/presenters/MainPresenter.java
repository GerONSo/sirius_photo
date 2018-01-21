package com.example.user.siriusphotos.presenters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.user.siriusphotos.models.deepai.APIHelper;
import com.example.user.siriusphotos.models.deepai.AnswerData;
import com.example.user.siriusphotos.utils.Box;
import com.example.user.siriusphotos.utils.FileUtils;
import com.example.user.siriusphotos.utils.Query;
import com.example.user.siriusphotos.utils.RecyclerViewData;
import com.example.user.siriusphotos.views.IMainView;

import java.io.File;
import java.net.URL;

@InjectViewState
public class MainPresenter extends MvpPresenter<IMainView> {

    public interface ImageReceiver {
        void acceptImage(File file);
    }
    private ImageViewPresenter imagePresenter;
    private RecyclerViewPresenter recyclerPresenter;

    public void setImagePresenter(ImageViewPresenter imagePresenter) {
        this.imagePresenter = imagePresenter;
    }

    public void setRecyclerPresenter(RecyclerViewPresenter recyclerPresenter) {
        this.recyclerPresenter = recyclerPresenter;
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

    public void onImageReadyFromCamera() {
        callback.acceptImage(file);
    }

    public void onImageReadyFromGallery(Uri contentUri) {
        getViewState().createFileByContentUri(contentUri, file);
        callback.acceptImage(file);
    }

    private File getTempPhotoFile() {
        Box<File> dir = new Box<>();
        getViewState().getTempFilesDir(dir);
        return FileUtils.getNewImageFile(dir.getValue(), "tmp_", ".jpg");
    }

    public void createFragment(){
        getViewState().createFragment();
    }

    public void query(RecyclerViewData q){
        if(q.getType() == Query.COLORIZER){
            APIHelper.getInstance().colorizer(imagePresenter.getMainImg().getAbsolutePath(), new APIHelper.OnLoad() {
                @Override
                public void onLoad(AnswerData a) {
                    imagePresenter.loadImage(a);
                }

                @Override
                public void onFailedLoad() {

                }

                @Override
                public void emptyFile() {

                }
            });
        }
    }
}
