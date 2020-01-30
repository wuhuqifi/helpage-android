package com.example.helper.interfacee.model;

import com.example.helper.beans.Device;
import com.example.helper.interfacee.presenter.IPreDev;

import javax.security.auth.callback.Callback;

public interface IDevice {
    void getDevice(Device device, IPreDev.CallBack callBack);
}
