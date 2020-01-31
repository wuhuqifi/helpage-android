package com.example.helper.interfacee.model;

import com.example.helper.beans.Device;
import com.example.helper.interfacee.presenter.IPDev;

public interface IMDev {
    void getDevice(Device device, IPDev.CallBack callBack);

    void login(Device device, IPDev.CallBack callBack);
}
