package com.example.helper.base;

public interface IModel<P,C>  {
    void getData(P args,C callback);
    interface CallBack{
        void onSucces(String res);
        void onFailure(String err);
    }
}
