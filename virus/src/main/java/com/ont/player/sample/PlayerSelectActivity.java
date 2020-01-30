package com.ont.player.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helper.R;
import com.ont.player.sample.mode.DeviceEntryInfo;
import com.ont.player.sample.utils.PermissionUtils;

public class PlayerSelectActivity extends AppCompatActivity {

    public static final String DEVICE_ID = "device_id";
    public static final String CHANNEL_ID = "channel_id";
    public static final String API_KEY = "api_key";

    private final int PAGE_LIVE = 1;//和mPageType默认值为0区别
    private final int PAGE_HISTORY = 2;

    private TextView mTextPageName;
    private LivePage mLivePage;
    private HistoryPage mHistoryPage;
    private DeviceEntryInfo mDeviceEntryInfo;
    private int mPageType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_select);

        Intent intent = getIntent();
        mDeviceEntryInfo = new DeviceEntryInfo();
        mDeviceEntryInfo.mApiKey = intent.getStringExtra(API_KEY);
        mDeviceEntryInfo.mChannelId = intent.getStringExtra(CHANNEL_ID);
        mDeviceEntryInfo.mDeviceId = intent.getStringExtra(DEVICE_ID);
        //为什么不用fragment？？？？？？？
        mLivePage = new LivePage(this);
        //将请求播放地址的参数传入子view 既然对子view来说是必要的，为啥不用构造函数的形式，而用注入的方式？？？？
        mLivePage.setDeviceEntryInfo(mDeviceEntryInfo);
        mHistoryPage = new HistoryPage(this);
        mHistoryPage.setDeviceEntryInfo(mDeviceEntryInfo);
        changePageType();

        FrameLayout mChildPage = findViewById(R.id.child_page);
        mChildPage.addView(mLivePage);//此时有一个是被隐藏的，只会显示一个
        mChildPage.addView(mHistoryPage);

        mTextPageName = findViewById(R.id.text_page_name);
        mTextPageName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                changePageType();
            }
        });

        ImageView imgGetBack = findViewById(R.id.img_get_back);
        imgGetBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    private void changePageType() {

        if (mPageType == PAGE_LIVE) {

            mPageType = PAGE_HISTORY;
            mLivePage.setVisibility(View.GONE);
            mHistoryPage.setVisibility(View.VISIBLE);
            mTextPageName.setText(getResources().getString(R.string.page_history));
        } else if (mPageType == PAGE_HISTORY){

            mPageType = PAGE_LIVE;
            mLivePage.setVisibility(View.VISIBLE);
            mHistoryPage.setVisibility(View.GONE);
            mTextPageName.setText(getResources().getString(R.string.page_live));
        } else {
            //第一次 这样就不用设置标志位 np
            mPageType = PAGE_LIVE;
            mLivePage.setVisibility(View.VISIBLE);
            mHistoryPage.setVisibility(View.GONE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       if (PermissionUtils.checkAndRequestPermission(this, PermissionUtils.PERMISSIONS_STORAGE, PermissionUtils.PERMISSIONS_STORAGE_CODE)) {

            mHistoryPage.updateData(1);//更新本地视频 view
       }
    }
}
