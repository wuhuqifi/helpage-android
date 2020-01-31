package com.example.helper.base;

public interface IPresenter<V> {
    void attatchView(V view);
    void disAttatchView();
    boolean isAttatchedView();
}
