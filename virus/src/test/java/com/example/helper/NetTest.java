package com.example.helper;

import android.util.Log;

import com.example.helper.beans.Device;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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
    public void postDataPoints(){
        String device_id = "582307500";
        String url = " http://api.heclouds.com/devices/"+device_id+"/datapoints/";
        url += "?type=3";//type为json,可为空
        String api_key = "A13PYmB=V3jmt7cI2m9czRtpUmE=";
        //请求体
        String json = "{\n" +
                "    \"datastreams\": [\n" +
                "        {\n" +
                "            \"id\": \"temperature\",\n" +
                "            \"datapoints\": [\n" +
                "                {\n" +
                "                    \"at\": \"2020-04-22T00:35:43\",\n" +
                "                    \"value\": \"36.5\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        //创建okhttp客户端对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建http请求体
        MediaType JSON =MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON,json);
        //创建ruquest请求 方法为POST body 为json
        Request request = new Request.Builder().url(url).method("POST",requestBody)
                .header("api-key",api_key).build();
        //创建call对象
        Call call = okHttpClient.newCall(request);
        //添加到请求队列
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("WANG", "请求失败，请检查网络");
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //获取返回的字符串
                String s = response.body().string();
                System.out.println(s);
            }
        });
    }

}
