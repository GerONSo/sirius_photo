package com.example.user.siriusphotos.models.deepai;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIService {

    @Headers("api-key: 24f81b43-d622-4a70-8e6a-7cd01e631550")
    @POST("/api/colorizer")
    @Multipart
    Call<AnswerData> colorizer(@Part MultipartBody.Part image);


    @Headers("api-key: 24f81b43-d622-4a70-8e6a-7cd01e631550")
    @POST("/api/deepdream")
    @Multipart
    Call<AnswerData> deepdream(@Part MultipartBody.Part image);


    @Headers("api-key: 24f81b43-d622-4a70-8e6a-7cd01e631550")
    @POST("/api/CNNMRF")
    @Multipart
    Call<AnswerData> CNNMRF(@Part MultipartBody.Part content_image, @Part MultipartBody.Part style_img);

    @Headers("api-key: 24f81b43-d622-4a70-8e6a-7cd01e631550")
    @POST("/api/fast-style-transfer")
    @Multipart
    Call<AnswerData> fastStyleTransfer(@Part MultipartBody.Part image, @Part MultipartBody.Part style);


}
