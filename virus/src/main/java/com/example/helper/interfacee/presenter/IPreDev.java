package com.example.helper.interfacee.presenter;

import com.example.helper.beans.Device;
import com.example.helper.interfacee.view.IFreshDev;

import java.security.PublicKey;

public interface IPreDev {
    void freshDev();
    interface CallBack{
        void onSucces(String data);
        void onFailure(String err);
    }
}
