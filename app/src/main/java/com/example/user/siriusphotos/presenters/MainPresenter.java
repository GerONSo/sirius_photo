package com.example.user.siriusphotos.presenters;

import android.net.Uri;
import android.widget.ImageView;

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

@InjectViewState
public class MainPresenter extends MvpPresenter<IMainView> {

    public interface ImageReceiver {
        void acceptImage(File file);
    }

    private ImageViewPresenter imagePresenter;
    private RecyclerViewPresenter recyclerPresenter;

    public String getUri() {
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

    public void createFragment() {
        getViewState().createFragment();
    }

    public void makeToast(String s) {
        getViewState().makeTost(s);
    }

    public void query(RecyclerViewData q) {
        if (imagePresenter.getMainImg() == null) {
            makeToast("Файл для редактирования не выбран");
            return;
        }
        if (q.getType() == Query.COLORIZER) {
            APIHelper.getInstance().colorizer(imagePresenter.getMainImg().getAbsolutePath(), new APIHelper.OnLoad() {
                @Override
                public void onLoad(AnswerData a) {
                    imagePresenter.loadImage(a);
                    imagePresenter.getViewState().finishLoad();
                }

                @Override
                public void onFailedLoad() {
                    getViewState().makeTost("Сервер временно не доступен. \nПроверьте подключение к интернету\n Или повторите попытку пойзже");
                    imagePresenter.getViewState().finishLoad();
                }

                @Override
                public void emptyFile() {
                    getViewState().makeTost("Файл для редактирования не выбран");
                    imagePresenter.getViewState().finishLoad();
                }
            });
        } else if (q.getType() == Query.ADDPHOTOFORGALLEREY) {
            selectImageFromGallery(new ImageReceiver() {
                @Override
                public void acceptImage(File file) {
                    imagePresenter.getViewState().startLoad();
                    recyclerPresenter.setImg(file);
                    APIHelper.getInstance().fastStyletransfer(imagePresenter.getMainImg(), recyclerPresenter.getImg(),
                            new APIHelper.OnLoad() {

                                @Override
                                public void onLoad(AnswerData a) {
                                    imagePresenter.loadImage(a);
                                    imagePresenter.getViewState().finishLoad();
                                }

                                @Override
                                public void onFailedLoad() {
                                    getViewState().makeTost("Сервер временно не доступен. \nПроверьте подключение к интернету\n Или повторите попытку пойзже");
                                    imagePresenter.getViewState().finishLoad();
                                }

                                @Override
                                public void emptyFile() {
                                    getViewState().makeTost("Файл для редактирования не выбран");
                                    imagePresenter.getViewState().finishLoad();
                                }
                            });
                }
            });

        }
    }
}
