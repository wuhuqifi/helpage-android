package com.example.helper.interfacee.view;

import android.os.Handler;

import com.example.helper.beans.Device;

public interface IFreshDev {
    Device getDevice();
    Handler getHandler();
    void fresh(String name, String ID, boolean isOnline);
}
