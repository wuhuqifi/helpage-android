package com.ont.player.sample.controller;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;

/**
 * Created by betali on 2018/5/2.
 */

public class LiveVideoController extends StandardVideoController {


    public LiveVideoController(@NonNull Context context) {

        super(context);
    }

    public LiveVideoController(@NonNull Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
    }

    public LiveVideoController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView() {

        super.initView();

        // 隐藏时间等view
        bottomProgress.setVisibility(GONE);
        videoProgress.setVisibility(INVISIBLE);
        totalTime.setVisibility(INVISIBLE);

        setLive();
    }
}
