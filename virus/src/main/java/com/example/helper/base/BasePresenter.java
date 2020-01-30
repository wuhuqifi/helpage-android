package com.example.helper.base;


public class BasePresenter<T> implements IPresenter<T> {
     private T mView;

    @Override
    public void attatchView(T view) {
        this.mView = view;
    }

    @Override
    public void disAttatchView() {
        this.mView = null;
    }

    @Override
    public boolean isAttatchedView() {
        return mView != null;
    }
}
