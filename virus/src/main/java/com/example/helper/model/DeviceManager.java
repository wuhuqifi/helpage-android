package com.example.helper.model;

import android.util.Log;

import com.example.helper.beans.Device;
import com.example.helper.interfacee.model.IMDev;
import com.example.helper.interfacee.presenter.IPDev;
import com.ConfigManager;
import com.example.helper.utils.NetProxy;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class DeviceManager implements IMDev {

    @Override
    public void getDevice(Device device, IPDev.CallBack callBack) {
        Request request = new Request.Builder()
                .url(ConfigManager.base.devices_url + "/" + device.ID)
                .method("GET",null)
                .header("api-key",device.apiKey)
                .build();
        NetProxy.Companion.doRequest(request, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callBack.onFailure("请求网络失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                callBack.onSucces(response.body().string());
            }
        });
    }

    @Override
    public void login(Device device, IPDev.CallBack callBack) {
        Request request = new Request.Builder()
                .url("http://mqtt.heclouds.com" )
                .method("GET",null)
                .header("ClientIdentifier",ConfigManager.base.device_id)
                .header("UserName",ConfigManager.base.product_id)
                .header("UserPassword",ConfigManager.base.master_key)
                .build();
        NetProxy.Companion.doRequest(request, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("WANG", e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.e("WANG", response.body().string());
            }
        });
    }
}
