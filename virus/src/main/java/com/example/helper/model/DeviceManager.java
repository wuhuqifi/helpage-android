package com.example.helper.model;

import android.util.Log;

import com.CustemApp;
import com.example.helper.beans.Device;
import com.example.helper.interfacee.model.IDevice;
import com.example.helper.interfacee.presenter.IPreDev;
import com.example.helper.utils.ConfigManager;
import com.example.helper.utils.NetProxy;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class DeviceManager implements IDevice {

    @Override
    public void getDevice(Device device, IPreDev.CallBack callBack) {
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
}
