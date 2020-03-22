package com.example.helper.ui.activity;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.helper.R;
import com.example.helper.adapter.DevInfoAdapter;
import com.example.helper.base.BaseActivity;
import com.example.helper.beans.DataPoint;
import com.example.helper.beans.DataStreams;
import com.example.helper.interfacee.presenter.IPDS;
import com.example.helper.interfacee.view.IVDS;
import com.example.helper.presenter.PreDS;
import com.example.helper.widget.FakeAppleListItemView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DevInfoActivity extends BaseActivity implements IVDS{
    private IPDS mIPDS;
    private Handler mHandler;
    private Timer mTimer;
    private RecyclerView mRecyclerView;
    private DevInfoAdapter mAdapter;
    @Override
    protected void initData() {
        mIPDS = new PreDS();
        mIPDS.attatchView(this);
        mHandler = new Handler();
        mTimer = new Timer();
    }
    @Override
    public Handler getmHandler() {
        return mHandler;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIPDS.disAttatchView();
        mTimer.cancel();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_dev_info;
    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.rv_devInfo);
    }

    @Override
    protected void initListener() {
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mIPDS.fresh();
            }
        }, 0, 1000);//每隔一秒刷新一次
    }



    @Override
    public void fresh(DataStreams dataStreams) {
        if (mAdapter == null) {
            mAdapter = new DevInfoAdapter(dataStreams);
        }
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.e("WANG", "fresh: "+mAdapter.toString() );
    }
}
