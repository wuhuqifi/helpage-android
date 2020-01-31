package com.example.helper.ui.activity;

import android.os.Handler;
import android.widget.TextView;

import com.example.helper.R;
import com.example.helper.base.BaseActivity;
import com.example.helper.beans.DataPoint;
import com.example.helper.beans.DataStreams;
import com.example.helper.interfacee.presenter.IPDS;
import com.example.helper.interfacee.view.IVDS;
import com.example.helper.presenter.PreDS;

public class DevInfoActivity extends BaseActivity implements IVDS{
    private TextView mTextView;
    private IPDS mIPDS;
    private Handler mHandler;
    @Override
    protected void initData() {
        mIPDS = new PreDS();
        mIPDS.attatchView(this);
        mHandler = new Handler();
    }
    @Override
    public Handler getmHandler() {
        return mHandler;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIPDS.disAttatchView();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_dev_info;
    }

    @Override
    protected void initView() {
        mTextView = findViewById(R.id.tv_datastream);
    }

    @Override
    protected void initListener() {
        mIPDS.fresh();
    }

    @Override
    public void fresh(DataStreams dataStreams) {
        mTextView.setTextSize(20);
        for (DataPoint dp:dataStreams.getList()) {
            mTextView.append(dp.getId() + ": "+dp.getCurrent_value()+"\n");
        }
    }
}
