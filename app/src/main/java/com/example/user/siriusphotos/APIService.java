package com.example.user.siriusphotos;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by user on 13.01.2018.
 */

public interface APIService {

    @Headers("api-key: 24f81b43-d622-4a70-8e6a-7cd01e631550")
    @POST("api/colorizer")
    @Multipart
    Call<AnswerData> colorizer(@Part MultipartBody.Part image);

    @Headers("api-key: 24f81b43-d622-4a70-8e6a-7cd01e631550")
    @POST("api/CNNMRF")
    @Multipart
    Call<AnswerData> CNNMRF(@Part MultipartBody.Part content_image, @Part MultipartBody.Part style_img);

}
