package com.example.helper.beans;

import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class CMD {
    private String cmd_uuid;//全平台唯一
    private int qos;//0 不关心是否响应 1 timeout内重发
    private int timeout;//秒
    private String payload;//json string bin

    public CMD(String payload){
        this.payload = payload;
    }

    public void send2Dev(){
        String device_id = "582307500";
        String url = "https://api.heclouds.com/cmds";
        String api_key = "A13PYmB=V3jmt7cI2m9czRtpUmE=";


        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/pain;charset=utf-8");//纯文本格式
        RequestBody requestBody = RequestBody.create(mediaType,payload);
        Request request = new Request.Builder()
                .url(url)
                .header("api-key",api_key)
                .header("device_id",device_id)
                .post(requestBody)
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("WANG", "jjjjjjjjjjjjjjjjjjjjjjjjjjj");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String s = response.body().string();
                Log.d("WANG", s);
            }
        });
    }

    public void send2Dev(String device_id,int timeout){
        String url = "http://api.heclouds.com/cmds";
        String api_key = "A13PYmB=V3jmt7cI2m9czRtpUmE=";

        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("text/pain;charset=utf-8");//纯文本格式
        RequestBody requestBody = RequestBody.create(payload,mediaType);
        Request request = new Request.Builder()
                .url(url)
                .method("POST",requestBody)
                .header("api-key",api_key)
                .header("device_id",device_id)
                .header("qos","1")
                .header("timeout", String.valueOf(timeout))
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("WANG", "jjjjjjjjjjjjjjjjjjjjjjjjjjj");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String s = response.body().string();
                Log.d("WANG", s);
            }
        });
    }
}
