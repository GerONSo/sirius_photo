package com.example.user.siriusphotos.presenters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Environment;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.user.siriusphotos.models.deepai.AnswerData;
import com.example.user.siriusphotos.utils.ImageUtils;
import com.example.user.siriusphotos.models.deepai.LoadHelper;
import com.example.user.siriusphotos.views.IImageView;

import java.io.File;
import java.io.IOException;


@InjectViewState
public class ImageViewPresenter extends MvpPresenter<IImageView> {

    private MainPresenter mainPresenter;
    private File mainImg;

    public void setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        mainPresenter.setImagePresenter(this);
    }

    public void setImage(Bitmap file) {
        getViewState().setImage(file);
    }

    public void loadImage(AnswerData answerData) {
        LoadHelper.getInstance().imageDownload(answerData.url, new LoadHelper.OnLoad() {
            @Override
            public void onLoad(File file) {
                    Log.d("Ok", "Callback");
                    setImage(file);

                getViewState().finishLoad();
            }

            @Override
            public void onFaile() {
                mainPresenter.makeToast("Не удалось загрузить изображение пожалуйста проверьте подключение к интернету");
                getViewState().finishLoad();
            }
        });
    }

    public File getMainImg() {
        return mainImg;
    }

    public void setImage(File file) {
        mainImg = file;
        Point size = new Point();
        getViewState().getViewSize(size);
        int maxSide = Math.max(size.x, size.y);
        Bitmap bitmap = null;
        mainPresenter.defoltList();
        Log.d("mytag", mainImg.getAbsolutePath());
        try {
            bitmap = ImageUtils.getScaledBitmap(mainImg, maxSide, maxSide);
        } catch (IOException e) {
            // TODO: 21-Jan-18 handle
        }
        getViewState().setImage(bitmap);
    }


    public void selectImageFromGallery() {
        mainPresenter.selectImageFromGallery(new MainPresenter.ImageReceiver() {
            @Override
            public void acceptImage(File file) {
                setImage(file);
            }
        });
    }

    public void selectImageFromCamera() {
        mainPresenter.selectImageFromCamera(new MainPresenter.ImageReceiver() {
            @Override
            public void acceptImage(File file) {
                setImage(file);
            }
        });
    }

    public void finishLoad() {
        getViewState().finishLoad();
    }

    public void startLoad() {
        getViewState().startLoad();
    }
}
