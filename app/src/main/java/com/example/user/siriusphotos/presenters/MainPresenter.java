package com.example.user.siriusphotos.presenters;

import android.graphics.BitmapFactory;
import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.user.siriusphotos.R;
import com.example.user.siriusphotos.models.deepai.APIHelper;
import com.example.user.siriusphotos.models.deepai.AnswerData;
import com.example.user.siriusphotos.utils.Box;
import com.example.user.siriusphotos.utils.FileUtils;
import com.example.user.siriusphotos.utils.Query;
import com.example.user.siriusphotos.utils.RecyclerViewData;
import com.example.user.siriusphotos.views.IMainView;

import java.io.File;

@InjectViewState
public class MainPresenter extends MvpPresenter<IMainView> {

    public void defoltList() {
        recyclerPresenter.setList();
        recyclerPresenter.drawList();
    }

    public interface ImageReceiver {
        void acceptImage(File file);
    }
    private ImageViewPresenter imagePresenter;
    private RecyclerViewPresenter recyclerPresenter;
    public String getUri(){
        return imagePresenter.getMainImg() != null ? imagePresenter.getMainImg().getAbsolutePath() : "";
    }
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
    }

    public void onFileCreatedByContentUri() {
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
    public void makeToast(String s){getViewState().makeTost(s);}
    public void query(final RecyclerViewData q){
        if(imagePresenter.getMainImg() == null){
            makeToast("Файл для редактирования не выбран");
            return;
        }
        recyclerPresenter.setList();
        if(q.getType() == Query.COLORIZER){
            imagePresenter.startLoad();
            q.setImg(BitmapFactory.decodeResource(recyclerPresenter.getResources(), R.drawable.colorizer_dim));
            recyclerPresenter.drawList();
            APIHelper.getInstance().colorizer(imagePresenter.getMainImg().getAbsolutePath(), new APIHelper.OnLoad() {
                @Override
                public void onLoad(AnswerData a) {
                    imagePresenter.loadImage(a);
                }

                @Override
                public void onFailedLoad() {
                    getViewState().makeTost("Сервер временно не доступен. \nПроверьте подключение к интернету\n Или повторите попытку пойзже");
                    imagePresenter.finishLoad();
                }

                @Override
                public void emptyFile() {
                    getViewState().makeTost("Файл для редактирования не выбран");
                    imagePresenter.finishLoad();
                }
            });
        }else if(q.getType() == Query.ADD_PHOTO_FROM_GALLERY){
            selectImageFromGallery(new ImageReceiver() {
                @Override
                public void acceptImage(File file) {
                    imagePresenter.startLoad();
                    q.setImg(BitmapFactory.decodeFile(file.getAbsolutePath()));
                    APIHelper.getInstance().fastStyletransfer(imagePresenter.getMainImg(), recyclerPresenter.getImg(),
                            new APIHelper.OnLoad() {

                                @Override
                                public void onLoad(AnswerData a) {
                                    imagePresenter.loadImage(a);
                                }

                                @Override
                                public void onFailedLoad() {
                                    getViewState().makeTost("Сервер временно не доступен. \nПроверьте подключение к интернету\n Или повторите попытку пойзже");
                                    imagePresenter.finishLoad();
                                }

                                @Override
                                public void emptyFile() {
                                    getViewState().makeTost("Файл для редактирования не выбран");
                                    imagePresenter.finishLoad();
                                }
                            });
                }
            });
        }

    }
}
