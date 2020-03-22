package com.example.helper.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helper.R;
import com.example.helper.base.BaseFragment;
import com.ConfigManager;
import com.ont.player.sample.PlayerActivity;
import com.ont.player.sample.def.IDataListener;
import com.ont.player.sample.def.IRequestDef;
import com.ont.player.sample.mode.DeviceEntryInfo;
import com.ont.player.sample.network.NetworkClient;
import com.ont.player.sample.network.PlayUrlRequest;


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
        mDeviceEntryInfo.mProtocol = "0";//0:RTMP 1:HLS 2:HTTPS-HLS
    }

    @Override
    protected void initListener() {
        mTextview.setOnClickListener(v -> {
            PlayUrlRequest request = new PlayUrlRequest(ConfigManager.video.master_key)
                    .setIs_live(true)
                    .setChannel_id(ConfigManager.video.channel_id)
                    .setDevice_id(ConfigManager.video.device_id)
                    .setProtocol_type(ConfigManager.video.play_protocol)
                    .setRequest_listener(new IDataListener() {

                        @Override
                        public void onComplete(int apiErr, int dataErr, String response) {

                            if (apiErr == IRequestDef.IRequestResultDef.ERR_OK) {
                                //打开播放活动
                                Intent intent = new Intent(getActivity(), com.ont.player.sample.PlayerActivity.class);
                                startActivity(intent.setData(Uri.parse(response))
                                        .putExtra(PlayerActivity.IS_LIVE, true)//直播
                                        .putExtra(PlayerActivity.IS_LOCAL, false)//本地视频
                                        .putExtra(PlayerActivity.PLAY_CYCLE, false)
                                        .putExtra(PlayerActivity.VIDEO_TITLE, "直播"));
                            } else {
                                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            NetworkClient.doRequest(request);
        });

    }

}
