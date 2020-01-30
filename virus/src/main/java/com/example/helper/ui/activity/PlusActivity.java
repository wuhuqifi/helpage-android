package com.example.helper.ui.activity;

import android.content.res.Resources;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.helper.R;
import com.example.helper.base.BaseActivity;
import com.example.helper.beans.CMD;
import com.example.helper.utils.ConfigManager;

public class PlusActivity extends BaseActivity {
    private TextView textView;
    @Override
    public int getLayoutID() {
        return R.layout.activity_plus;
    }

    @Override
    protected void initView() {
        textView = findViewById(R.id.tv_test);
        textView.setText(ConfigManager.getVideoConfig(this,"master_key"));
    }


}