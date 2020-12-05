package com.example.helper.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ConfigManager;
import com.example.helper.R;
import com.example.helper.base.BaseFragment;
import com.example.helper.beans.DataPoint;
import com.example.helper.utils.NetProxy;
import com.example.helper.widget.CornerToggleView;
import com.example.helper.widget.DataView;
import com.ont.player.sample.PlayerActivity;
import com.ont.player.sample.def.IDataListener;
import com.ont.player.sample.def.IRequestDef;
import com.ont.player.sample.mode.DeviceEntryInfo;
import com.ont.player.sample.network.NetworkClient;
import com.ont.player.sample.network.PlayUrlRequest;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class Fragment_watch extends BaseFragment {
    Handler mHandler;
    Timer timer ;
    DeviceEntryInfo mDeviceEntryInfo;
    private TextView mTextview;
    private CornerToggleView mDrug;
    private CornerToggleView mSleep;
    private CornerToggleView mBrush;
    private DataView mTemp;
    private DataView mHumi;
    private DataView mSmoke;
    private DataView mPic;//摔倒


    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_watch, container, false);
        mTextview = v.findViewById(R.id.tv_camera);
        mDrug = v.findViewById(R.id.ctv_drug);
        mSleep = v.findViewById(R.id.ctv_sleep);
        mBrush = v.findViewById(R.id.ctv_brush);
        mTemp = v.findViewById(R.id.dv_tem);
        mHumi = v.findViewById(R.id.dv_humi);
        mSmoke = v.findViewById(R.id.dv_smoke);
        mPic = v.findViewById(R.id.dv_pic);

        //定时刷新
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if (msg.what == 1){//定时消息
                    fresh("温度");
                    fresh("湿度");
                    fresh("烟雾");
                    fresh("摔倒");
                }
                if (msg.what == 2){//数据消息
                    DataPoint dp = (DataPoint) msg.obj;
                    if (dp.getId().equals("温度")) mTemp.setNumber(dp.getCurrent_value());
                    if (dp.getId().equals("湿度"))mHumi.setNumber(dp.getCurrent_value());
                    if (dp.getId().equals("烟雾")) mSmoke.setNumber(dp.getCurrent_value());
                    if (dp.getId().equals("摔倒")) mPic.setNumber(dp.getCurrent_value());
                }
            }
        };
        timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;//定时消息
                mHandler.sendMessage(message);
            }
        }, 0,1000);
        return v;
    }

    @Override
    protected void initData() {
        mDeviceEntryInfo = new DeviceEntryInfo();
        mDeviceEntryInfo.mApiKey = "M0M1m2zdiVDhzQmBYp=RBuLlwxQ=";
        mDeviceEntryInfo.mChannelId = "1";
        mDeviceEntryInfo.mProtocol = "0";//0:RTMP 1:HLS 2:HTTPS-HLS
        mDeviceEntryInfo.mDeviceId = "583012656";
    }

    @Override
    protected void initListener() {
        setToggleViewListener(mDrug);
        setToggleViewListener(mSleep);
        setToggleViewListener(mBrush);

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
                                Intent intent = new Intent(getActivity(), PlayerActivity.class);
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
    void setToggleViewListener(CornerToggleView ctv){
        ctv.setOnClickListener(view -> {
            if (ctv.isBtnON) {
                ctv.CTVbuttonCurrent = ctv.CTVbuttonOFF;
                ctv.invalidate();
                ctv.isBtnON = false;
                remind(ctv);
            } else {
                ctv.CTVbuttonCurrent = ctv.CTVbuttonON;
                ctv.invalidate();
                ctv.isBtnON = true;
                remind(ctv);
            }
        });
    }
    void remind(CornerToggleView ctv){
        String value;
        if (ctv.isBtnON){
            value = "1";
        }else {
            value ="0";
        }
        switch (ctv.getId()) {
            case R.id.ctv_drug:
                NetProxy.Companion.sendTopicWithoutCallback("data","{\"m\":"+value+"}");
                break;
            case R.id.ctv_sleep:
                NetProxy.Companion.sendTopicWithoutCallback("data","{\"s\":"+value+"}");
                break;
            case R.id.ctv_brush:
                NetProxy.Companion.sendTopicWithoutCallback("data","{\"b\":"+value+"}");
                break;
        }
        Toast.makeText(getContext(),"已发送消息",Toast.LENGTH_SHORT).show();
    }

    private void fresh(String id){
        NetProxy.Companion.queryDP(id, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                DataPoint dp = new DataPoint();
                String res = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(res);
                    JSONObject data = jsonObject.getJSONObject("data");
                    dp.parseDataPoint(data);
                }catch (Exception e){}
                Message message = new Message();
                message.what = 2;//刷新消息
                message.obj = dp;
                mHandler.sendMessage(message);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("wang", "onPause: " );
    }
}
