package com.ont.player.sample;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

    private final int PAGE_LIVE = 1;
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

        mLivePage = new LivePage(this);
        mLivePage.setDeviceEntryInfo(mDeviceEntryInfo);
        mHistoryPage = new HistoryPage(this);
        mHistoryPage.setDeviceEntryInfo(mDeviceEntryInfo);
        changePageType();

        FrameLayout mChildPage = findViewById(R.id.child_page);
        mChildPage.addView(mLivePage);
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

            mPageType = PAGE_LIVE;
            mLivePage.setVisibility(View.VISIBLE);
            mHistoryPage.setVisibility(View.GONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

       if (PermissionUtils.checkAndRequestPermission(this, PermissionUtils.PERMISSIONS_STORAGE, PermissionUtils.PERMISSIONS_STORAGE_CODE)) {

            mHistoryPage.updateData(1);
       }
    }
}
