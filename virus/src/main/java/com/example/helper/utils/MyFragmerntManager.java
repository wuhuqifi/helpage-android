package com.example.helper.utils;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AppCompatActivity;

import com.example.helper.base.BaseFragment;

import org.jetbrains.annotations.NotNull;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class MyFragmerntManager {
    private  static FragmentManager mManager;
    private static BaseFragment mFragment_service;
    private static BaseFragment mFragment_my;
    private static BaseFragment mFragment_watch;
    /*
     * 使用替换方法，将需要显示的Fragment显示出来
     * 第一个参数是显示fragment的布局
     * 第二个参数是要显示fragment
     * */
    //只能在activity里调用
    public static void initFragment(@NotNull AppCompatActivity appCompatActivity, int fragment_container_id, Fragment fragment){
        //保证只实例化一个mManager
        if (mManager == null){
            mManager = appCompatActivity.getSupportFragmentManager();
        }
        FragmentTransaction transaction;
        transaction= mManager.beginTransaction();        //开启事务
        transaction.replace(fragment_container_id, fragment);
        transaction.commitAllowingStateLoss ();//提交事务
    }
}
