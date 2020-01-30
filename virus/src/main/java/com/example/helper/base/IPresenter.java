package com.example.helper.base;

public interface IPresenter<T> {
    void attatchView(T view);
    void disAttatchView();
    boolean isAttatchedView();
}
