package com.example.helper.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.helper.R;

import java.util.Random;

public class SplashActivity extends AppCompatActivity implements ViewPropertyAnimatorListener {
    ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(mImageView);
    }

    protected int getLayoutID() {
        return R.layout.activity_splash;
    }

    protected void initData() {
        mImageView  = new ImageView(SplashActivity.this);
        int res[] = {R.mipmap.lol2, R.mipmap.lol3,
                R.mipmap.lol5,R.mipmap.lol6,
                R.mipmap.shift1,R.mipmap.shift2,
        };

        Random random = new Random();
        int rand = random.nextInt(res.length);
        mImageView.setBackgroundResource(res[rand]);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mImageView.setLayoutParams(layoutParams);
        mImageView.setScaleType(ImageView.ScaleType.MATRIX);

        //设置动画缩放
        ViewCompat.animate(mImageView).scaleX(1f).scaleY(1f).setDuration(10).setListener(this);
    }

    @Override
    public void onAnimationStart(View view) {

    }

    @Override
    public void onAnimationEnd(View view) {
    //进入主界面
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        //销毁活动
        finish();
    }

    @Override
    public void onAnimationCancel(View view) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
