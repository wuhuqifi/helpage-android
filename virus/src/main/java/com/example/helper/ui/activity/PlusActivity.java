package com.example.helper.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helper.R;
import com.example.helper.base.BaseActivity;
import com.ConfigManager;
import com.example.helper.base.IModel;
import com.example.helper.utils.NetProxy;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PlusActivity extends BaseActivity {
    private Button bt;
    @Override
    public int getLayoutID() {
        return R.layout.activity_plus;
    }

    @Override
    protected void initView() {
        bt = findViewById(R.id.bt_plus);
    }

    @Override
    protected void initListener() {
       // bt.setOnClickListener();
    }
}