package com.example.user.siriusphotos.models.deepai;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 13.01.2018.
 */

public class AnswerData {
    @SerializedName("job_id")
    public int id;
    @SerializedName("output_url")
    public String url;

    @Override
    public String toString() {
        return id + " " + url;
    }
}
