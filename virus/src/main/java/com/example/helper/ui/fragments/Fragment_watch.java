package com.example.helper.ui.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helper.R;
import com.example.helper.base.BaseFragment;
import com.ont.player.sample.PlayerActivity;
import com.ont.player.sample.def.IDataListener;
import com.ont.player.sample.def.IRequestDef;
import com.ont.player.sample.mode.DeviceEntryInfo;
import com.ont.player.sample.network.LevelRequest;
import com.ont.player.sample.network.NetworkClient;
import com.ont.player.sample.network.PlayUrlRequest;

import okhttp3.Headers;


public class Fragment_watch extends BaseFragment {
    DeviceEntryInfo mDeviceEntryInfo;
    private TextView mTextview;
    private String mURL;
    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.frag_watch,container,false);
        mTextview = v.findViewById(R.id.tv_camera);
        return v ;
    }

    @Override
    protected void initData() {
        mDeviceEntryInfo = new DeviceEntryInfo();
        mDeviceEntryInfo.mApiKey = "M0M1m2zdiVDhzQmBYp=RBuLlwxQ=";
        mDeviceEntryInfo.mChannelId = "1";
        mDeviceEntryInfo.mDeviceId = "583012656";
        mDeviceEntryInfo.mLevel = "3";
        mDeviceEntryInfo.mProtocol = "0";//0:RTMP 1:HLS 2:HTTPS-HLS
        mURL = "rtmp://v-lzw.cmcconenet.com:1936/live/583012656-1" +
                "?eyJrZXkiOjI2MzIsInNpZ24iOiI2Mk5LWlVoaTdReTRBMjN4SnRNZ0ppQzRva" +
                "2x1SkZDQmgzdzhCU01OTnJYN3k3bXd2ZFFkaGN6TVdzMkY1Q2JrSUpSZkRiLWlJbjVMaTBr" +
                "OUV2NVRCMTZSdjFiSi1VSHpVOWVpX3JUUHU0TGlFSTJNbWdackpfOVFCZWRWOUw4Yk95VDZMWW" +
                "NyNjN3elZfSXpuMzlYOXlaRERPeWctQmxBVkJSR2Rqc1FITVF3dTI5WEp1ODJLMGZOR0doOXBLZEoifQ";
    }

    @Override
    protected void initListener() {
        mTextview.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), com.ont.player.sample.PlayerActivity.class);
            startActivity(intent.setData(Uri.parse(mURL))
                    .putExtra(PlayerActivity.IS_LIVE, true)//直播
                    .putExtra(PlayerActivity.IS_LOCAL, false)//本地视频
                    .putExtra(PlayerActivity.PLAY_CYCLE, false)
                    .putExtra(PlayerActivity.VIDEO_TITLE, "直播"));
        });
    }
}
