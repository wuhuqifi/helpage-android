package com.example.helper;

import android.util.Log;

import com.example.helper.beans.Device;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetTest {
    @Test
    public void queryDevice(){
        String ID = "582307500";
        String api_key = "A13PYmB=V3jmt7cI2m9czRtpUmE=";
        String url = "http://api.heclouds.com/devices/" + ID;

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).method("GET",null)
                .header("api-key",api_key).build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("WANG", "jjjjjjjjjjjjjjjjjjjjjjjjjjj");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String s = response.body().string();
                System.out.println(s);
            }
        });
        //
        //单元测试不支持多线程
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void queryDataPoints(){
        String device_id = "582307500";
        String url = " http://api.heclouds.com/devices/"+device_id+"/datastreams/";
        String api_key = "A13PYmB=V3jmt7cI2m9czRtpUmE=";

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).method("GET",null)
                .header("api-key",api_key).build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("WANG", "jjjjjjjjjjjjjjjjjjjjjjjjjjj");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String s = response.body().string();
                System.out.println(s);
            }
        });
        //
        //单元测试不支持多线程
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void sendCMD(){

    }
}
