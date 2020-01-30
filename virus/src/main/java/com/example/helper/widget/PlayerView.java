package com.example.helper.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.helper.R;

import org.xmlpull.v1.XmlPullParser;
//TODO not USED yet
public class PlayerView extends FrameLayout {
    private Context mContext;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private TextView textView;

    public PlayerView(@NonNull Context context) {
        this(context,null);
    }

    public PlayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PlayerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        mSurfaceView = new SurfaceView(mContext);
        mSurfaceHolder = mSurfaceView.getHolder();//操作Surface的接口
        textView = new TextView(mContext);
        setSurfaceView();
        setFrontView();//index大的view会覆盖之前的view
    }

    private void setFrontView() {
        textView.setText("我是前台");
        textView.setGravity(Gravity.CENTER);//设置文字居中
        textView.setBackground(getContext().getDrawable(R.drawable.rect_corner));
        //第1个子view
        addView(textView,1,new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
    }


    private void setSurfaceView() {
        mSurfaceHolder.setKeepScreenOn(true);
        mSurfaceView.setBackgroundColor(Color.RED);
        LayoutParams l = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        addView(mSurfaceView,0,l);//加载表面？
    }
}
