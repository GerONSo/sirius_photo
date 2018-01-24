package com.example.user.siriusphotos.presenters;

import android.graphics.BitmapFactory;
import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.user.siriusphotos.R;
import com.example.user.siriusphotos.models.deepai.AnswerData;
import com.example.user.siriusphotos.models.deepai.QueryHelper;
import com.example.user.siriusphotos.utils.Box;
import com.example.user.siriusphotos.utils.FileUtils;
import com.example.user.siriusphotos.utils.Query;
import com.example.user.siriusphotos.utils.RecyclerViewData;
import com.example.user.siriusphotos.views.IMainView;

import java.io.File;

@InjectViewState
public class MainPresenter extends MvpPresenter<IMainView> {

    private ImageViewPresenter imagePresenter;
    private RecyclerViewPresenter recyclerPresenter;
    private QueryHelper queryHelper;

    private ImageReceiver callback;
    private File file;

    public interface ImageReceiver {
        void acceptImage(File file);
    }
    public void setDefoltList(){
        recyclerPresenter.setList();
    }
    public void startLoad() {
        imagePresenter.startLoad();
    }

    public void loadImage(AnswerData a) {
        imagePresenter.loadImage(a);
    }

    public void finishLoad() {
        imagePresenter.finishLoad();
    }

    public String getUri() {
        return imagePresenter.getMainImg() != null ? imagePresenter.getMainImg().getAbsolutePath() : "";
    }


    public void setImagePresenter(ImageViewPresenter imagePresenter) {
        this.imagePresenter = imagePresenter;
    }

    public void setRecyclerPresenter(RecyclerViewPresenter recyclerPresenter) {
        this.recyclerPresenter = recyclerPresenter;
    }

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
    }

    public void onFileCreatedByContentUri() {
        callback.acceptImage(file);
    }

    private File getTempPhotoFile() {
        Box<File> dir = new Box<>();
        getViewState().getTempFilesDir(dir);
        return FileUtils.getNewImageFile(dir.getValue(), "tmp_", ".jpg");
    }

    public void createFragment() {
        getViewState().createFragment();
    }

    public void makeToast(String s) {
        getViewState().makeTost(s);
    }

    public void query(final RecyclerViewData q) {
        if (queryHelper == null) {
            queryHelper = new QueryHelper(this);
        }
        if (imagePresenter.isLoad()) {
            makeToast("Дождитесь окончания загрузки");
            return;
        }
        if (imagePresenter.getMainImg() == null) {
            makeToast("Файл для редактирования не выбран");
            return;
        }
        recyclerPresenter.setList();
        if (q.getType() == Query.COLORIZER) {
            startLoad();
            q.setImg(BitmapFactory.decodeResource(recyclerPresenter.getResources(), R.drawable.colorizer_dim));
            recyclerPresenter.update();
            queryHelper.colorizer(imagePresenter.getMainImg());
        } else if (q.getType() == Query.LA_MUSE) {
            imagePresenter.startLoad();
            q.setImg(BitmapFactory.decodeResource(recyclerPresenter.getResources(), R.drawable.la_muse_dim));
            recyclerPresenter.update();
            queryHelper.laMuse(imagePresenter.getMainImg());
        } else if (q.getType() == Query.WAVE) {
            imagePresenter.startLoad();
            q.setImg(BitmapFactory.decodeResource(recyclerPresenter.getResources(), R.drawable.wave_dim));
            recyclerPresenter.update();
            queryHelper.wave(imagePresenter.getMainImg());
        }else if(q.getType() == Query.RAIN_PRINCESS){
            imagePresenter.startLoad();
            q.setImg(BitmapFactory.decodeResource(recyclerPresenter.getResources(), R.drawable.rain_princess_dim));
            recyclerPresenter.update();
            queryHelper.rainPrincess(imagePresenter.getMainImg());
        }else if(q.getType() == Query.THE_SCREAM){
            imagePresenter.startLoad();
            q.setImg(BitmapFactory.decodeResource(recyclerPresenter.getResources(), R.drawable.the_scream_dim));
            recyclerPresenter.update();
            queryHelper.scream(imagePresenter.getMainImg());
        }else if(q.getType() == Query.DEEP_DREAM){
            imagePresenter.startLoad();
            q.setImg(BitmapFactory.decodeResource(recyclerPresenter.getResources(), R.drawable.dogs_playing_poker_dim));
            recyclerPresenter.update();
            queryHelper.deapDream(imagePresenter.getMainImg());
        }else{
            makeToast("Данная функция находиться в разработке");
        }

    }
}
