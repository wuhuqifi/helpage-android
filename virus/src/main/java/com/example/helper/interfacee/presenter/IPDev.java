package com.example.helper.interfacee.presenter;

public interface IPDev {
    void freshDev();

    void login();

    interface CallBack{
        void onSucces(String data);
        void onFailure(String err);
    }
}
