package com.example.user.siriusphotos.models.deepai;

import com.example.user.siriusphotos.presenters.MainPresenter;

import java.io.File;

/**
 * Created by user on 22.01.2018.
 */

public class QueryHelper {
    public QueryHelper(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    private MainPresenter mainPresenter;

    private final String LA_MUSE = "la-muse.ckpt";
    private final String WAWE = "wave.ckpt";
    private final String RAIN_PRINCESS = "rain-princess.ckpt";
    private final String SCREAM = "scream.ckpt";


    public void wave(File file) {
        APIHelper.getInstance().fastStyletransfer(file, WAWE,
                new APIHelper.OnLoad() {

                    @Override
                    public void onLoad(AnswerData a) {
                        mainPresenter.loadImage(a);
                    }

                    @Override
                    public void onFailedLoad() {
                        mainPresenter.makeToast("Сервер временно не доступен. \nПроверьте подключение к интернету\n Или повторите попытку пойзже");
                        mainPresenter.setDefoltList();
                        mainPresenter.finishLoad();
                    }

                    @Override
                    public void emptyFile() {
                        mainPresenter.makeToast("Файл для редактирования не выбран");
                        mainPresenter.setDefoltList();
                        mainPresenter.finishLoad();
                    }
                });
    }

    public void laMuse(File file) {
        APIHelper.getInstance().fastStyletransfer(file, LA_MUSE,
                new APIHelper.OnLoad() {

                    @Override
                    public void onLoad(AnswerData a) {
                        mainPresenter.loadImage(a);
                    }

                    @Override
                    public void onFailedLoad() {
                        mainPresenter.makeToast("Сервер временно не доступен. \nПроверьте подключение к интернету\n Или повторите попытку пойзже");
                        mainPresenter.setDefoltList();
                        mainPresenter.finishLoad();
                    }

                    @Override
                    public void emptyFile() {
                        mainPresenter.makeToast("Файл для редактирования не выбран");
                        mainPresenter.setDefoltList();
                        mainPresenter.finishLoad();
                    }
                });
    }

    public void colorizer(File file) {
        APIHelper.getInstance().colorizer(file, new APIHelper.OnLoad() {
            @Override
            public void onLoad(AnswerData a) {
                mainPresenter.loadImage(a);
            }

            @Override
            public void onFailedLoad() {
                mainPresenter.makeToast("Сервер временно не доступен. \nПроверьте подключение к интернету\n Или повторите попытку пойзже");
                mainPresenter.setDefoltList();
                mainPresenter.finishLoad();
            }

            @Override
            public void emptyFile() {
                mainPresenter.makeToast("Файл для редактирования не выбран");
                mainPresenter.setDefoltList();
                mainPresenter.finishLoad();
            }
        });
    }

    public void rainPrincess(File file) {

        APIHelper.getInstance().fastStyletransfer(file, RAIN_PRINCESS,
                new APIHelper.OnLoad() {

                    @Override
                    public void onLoad(AnswerData a) {
                        mainPresenter.loadImage(a);
                    }

                    @Override
                    public void onFailedLoad() {
                        mainPresenter.makeToast("Сервер временно не доступен. \nПроверьте подключение к интернету\n Или повторите попытку пойзже");
                        mainPresenter.setDefoltList();
                        mainPresenter.finishLoad();
                    }

                    @Override
                    public void emptyFile() {
                        mainPresenter.makeToast("Файл для редактирования не выбран");
                        mainPresenter.setDefoltList();
                        mainPresenter.finishLoad();
                    }
                });
    }

    public void scream(File file) {
        APIHelper.getInstance().fastStyletransfer(file, RAIN_PRINCESS,
                new APIHelper.OnLoad() {

                    @Override
                    public void onLoad(AnswerData a) {
                        mainPresenter.loadImage(a);
                    }

                    @Override
                    public void onFailedLoad() {
                        mainPresenter.makeToast("Сервер временно не доступен. \nПроверьте подключение к интернету\n Или повторите попытку пойзже");
                        mainPresenter.setDefoltList();
                        mainPresenter.finishLoad();
                    }

                    @Override
                    public void emptyFile() {
                        mainPresenter.makeToast("Файл для редактирования не выбран");
                        mainPresenter.setDefoltList();
                        mainPresenter.finishLoad();
                    }
                });
    }

    public void deapDream(File file) {
        APIHelper.getInstance().deepdream(file, new APIHelper.OnLoad() {

            @Override
            public void onLoad(AnswerData a) {
                mainPresenter.loadImage(a);
            }

            @Override
            public void onFailedLoad() {
                mainPresenter.makeToast("Сервер временно не доступен. \nПроверьте подключение к интернету\n Или повторите попытку пойзже");
                mainPresenter.setDefoltList();
                mainPresenter.finishLoad();
            }

            @Override
            public void emptyFile() {
                mainPresenter.makeToast("Файл для редактирования не выбран");
                mainPresenter.setDefoltList();
                mainPresenter.finishLoad();
            }
        });
    }
}