package com.example.helper.ui;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

public class CustemApp extends Application {
    public static Typeface gTypeface;//全局字体类型 创建应用时加载这个类
    private static Context mContext;
    public static Context getAppContext() {
        return mContext;
    };
    @Override
    public void onCreate() {
        super.onCreate();
        setFont();
        mContext = getAppContext();
    }

    private void setFont() {
        gTypeface = Typeface.createFromAsset(getAssets(),"fonts/hanbuger.ttf");

        try {
            //反射
            Field field = Typeface.class.getDeclaredField("SANS_SERIF");
            field.setAccessible(true);
            field.set(null, gTypeface);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
