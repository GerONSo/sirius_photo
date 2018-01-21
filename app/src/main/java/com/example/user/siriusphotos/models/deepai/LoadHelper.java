package com.example.user.siriusphotos.models.deepai;

/**
 * Created by user on 21.01.2018.
 */

public final class LoadHelper {

    private LoadHelper loadHelper;

    private LoadHelper() {
    }

    public LoadHelper getInstance(){
        if(loadHelper == null)
            loadHelper = new LoadHelper();
        return loadHelper;
    }

    public interface OnLoad {
        void onLoad();
    }

    public void load(String url) {

    }
}