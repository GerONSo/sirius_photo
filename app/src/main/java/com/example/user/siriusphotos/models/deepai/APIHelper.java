package com.example.user.siriusphotos.models.deepai;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
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

    private Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public interface OnLoad {
        void onLoad(AnswerData a);

        void onFailedLoad();

        void emptyFile();
    }


    private APIHelper() {
    }

    public static APIHelper getInstance() {
        if (instance == null) {
            instance = new APIHelper();
        }
        return instance;
    }

    //на данном этапе API не может реализовать этот метод поэтому использование данного метода приведет к краху приложения
    public void CNMRF(String imageContent, String imageStyle, final OnLoad callback) {
        Retrofit retrofit = getRetrofit();
        APIService service = retrofit.create(APIService.class);
        File imgContent = new File(imageContent);
        File imgStyle = new File(imageStyle);
        if (!imgContent.isFile() || !imgStyle.isFile()) {
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


    public void fastStyletransfer(File file1, File file2, final OnLoad callback) {
        Retrofit retrofit = getRetrofit();
        APIService service = retrofit.create(APIService.class);
        if (!file1.isFile() || !file2.isFile()) {
            Log.d("file", "empty file");
            callback.emptyFile();
            return;
        }
        ArrayList<MultipartBody.Part> fileList = new ArrayList<>();

        RequestBody reqFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        MultipartBody.Part body1 = MultipartBody.Part.createFormData("image1", file1.getName(), reqFile1);
        fileList.add(body1);
 ;
        RequestBody reqFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
        MultipartBody.Part body2 = MultipartBody.Part.createFormData("image2", file2.getName(), reqFile2);
        fileList.add(body2);

        Call<AnswerData> result = service.fastStyleTransfer(fileList);
        result.enqueue(new Callback<AnswerData>() {
            @Override
            public void onResponse(Call<AnswerData> call, Response<AnswerData> response) {
                Log.d("fastStyle", "Ok");
                if (response.body().url != null)
                    callback.onLoad(response.body());
                else{
                    callback.onFailedLoad();
                }
            }

            @Override
            public void onFailure(Call<AnswerData> call, Throwable t) {
                Log.d("fastStyle", "YOU LOOSE");
                callback.onFailedLoad();
            }
        });
    }

    public void colorizer(String uri, final OnLoad callback) {
        Retrofit retrofit = getRetrofit();
        APIService service = retrofit.create(APIService.class);
        File file = new File(uri);
        Log.i("uri", file.getPath());
        if (!file.isFile()) {
            Log.d("file", "empty file");
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
