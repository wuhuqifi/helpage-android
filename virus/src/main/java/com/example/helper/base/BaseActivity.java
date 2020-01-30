package com.example.helper.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();//当前Activity是否需要全屏
        initData();//先准备数据
        setContentView(getLayoutID());//显示界面
        initView();//在找到需要的view
        initListener();//注册监听
    }

    protected void setFullScreen() {
    }

    protected void initView(){}

    protected void initData() {
    }
    /*adapter listener相关*/
    protected void initListener() {
    }

    /*获取布局id*/
    protected abstract int getLayoutID();

}
