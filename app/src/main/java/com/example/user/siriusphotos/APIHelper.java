package com.example.user.siriusphotos;

import android.util.Log;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIHelper {
    private static final String HOST = "https://api.deepai.org/";
    private static APIHelper instance;

    private Retrofit getRetrofit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .readTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    interface OnLoad{
        void onLoad(AnswerData a);
        void onFailedLoad();
        void emptyFile();
    }


    private APIHelper(){}

    public static APIHelper getInstance(){
        if(instance == null){
            instance = new APIHelper();
        }
        return instance;
    }


    public void CNMRF(String imageContent, String imageStyle, final OnLoad callback){
        Retrofit retrofit = getRetrofit();
        APIService service = retrofit.create(APIService.class);
        File imgContent = new File(imageContent);
        File imgStyle = new File(imageStyle);
        if(!imgContent.isFile() || !imgStyle.isFile()){
            callback.emptyFile();
            return;
        }

        RequestBody reqContent = RequestBody.create(MediaType.parse("multipart/form-data"), imgContent);
        RequestBody reqStyle = RequestBody.create(MediaType.parse("multipart/form-data"), imgStyle);
        MultipartBody.Part content = MultipartBody.Part.createFormData("content_image", imgContent.getName(), reqContent);
        MultipartBody.Part style = MultipartBody.Part.createFormData("style_image", imgStyle.getName(), reqStyle);
        Call<AnswerData> result = service.CNNMRF(content, style);
        result.enqueue(new Callback<AnswerData>() {
            @Override
            public void onResponse(Call<AnswerData> call, Response<AnswerData> response) {
                callback.onLoad(response.body());
            }

            @Override
            public void onFailure(Call<AnswerData> call, Throwable t) {
                callback.onFailedLoad();
            }
        });
    }

    public void colorizer(String uri, final OnLoad callback){


        Retrofit retrofit = getRetrofit();
        APIService service = retrofit.create(APIService.class);
        File file = new File(uri);
        Log.i("uri",file.getPath());
        if(!file.isFile()){
            Log.d("file","empty file");
            callback.emptyFile();
            return;
        }

        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), reqFile);
        Call<AnswerData> result = service.colorizer(body);
        result.enqueue(new Callback<AnswerData>() {
            @Override
            public void onResponse(Call<AnswerData> call, Response<AnswerData> response) {
               Log.d("colorizer", "Ok");
                callback.onLoad(response.body());
            }

            @Override
            public void onFailure(Call<AnswerData> call, Throwable t) {
                Log.d("colorize", "YOU LOOSE");
                callback.onFailedLoad();
            }
        });
    }
}
