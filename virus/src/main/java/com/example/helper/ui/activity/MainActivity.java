package com.example.helper.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.FloatRange;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helper.R;
import com.example.helper.base.BaseActivity;
import com.example.helper.ui.fragments.Fragment_my;
import com.example.helper.ui.fragments.Fragment_device;
import com.example.helper.ui.fragments.Fragment_watch;

public class MainActivity extends BaseActivity implements KeyEvent.Callback {

    private ImageView mMenu;
    private ImageView mPlus;
    private TextView tv_bar_title;
    private DrawerLayout mDrawerLayout;
    private BottomNavigationView mBottomNavigationView;
    private View mFragContainer;

    private Fragment mFragDevice;
    private Fragment mFragWatch;
    private Fragment mFragMy;
    private FragmentManager mManager;

    @Override
    protected void initData() {
        Log.e("WANG", "initData: onActivity");
        mManager= getSupportFragmentManager();     //初始化bottomnavi管理者
        mFragDevice = new Fragment_device();
        mFragWatch = new Fragment_watch();
        mFragMy = new Fragment_my();

    }

    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //必须先加载布局文件，才能findViewbyid（）
        mFragContainer = findViewById(R.id.frag_container);//frag的容器
        tv_bar_title = findViewById(R.id.tv_bar_title);
        mMenu = findViewById(R.id.iv_menu);
        mPlus = findViewById(R.id.iv_plus);
        mBottomNavigationView = findViewById(R.id.bot_navi);
        mDrawerLayout = findViewById(R.id.drawer);

        //第一次进入时显示主界面
        showFragment(mFragContainer.getId(),mFragDevice);
        tv_bar_title.setText("设备配网");

    }

    protected void initListener() {
        //底部导航栏监听
        mBottomNavigationView.setOnNavigationItemSelectedListener( menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.bot_navi_item_1:
                    showFragment(mFragContainer.getId(),mFragDevice);
                    tv_bar_title.setText("设备配网");
                    return true;
                case R.id.bot_navi_item_2:
                    showFragment(mFragContainer.getId(),mFragWatch);
                    tv_bar_title.setText("实时信息");
                    return true;
                case R.id.bot_navi_item_3:
                    showFragment(mFragContainer.getId(),mFragMy);
                    tv_bar_title.setText("个人设置");
                    return true;
            }
            return false;
        });
        //toolbar plus监听
        mPlus.setOnClickListener( v -> {
                    Intent intent = new Intent(MainActivity.this, PlusActivity.class);
                    startActivity(intent);
            }
        );
        //toolbar menu监听
        mMenu.setOnClickListener(v -> {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        });
    }

    /*
     * 使用替换方法，将需要显示的Fragment显示出来
     * 第一个参数是显示fragment的布局
     * 第二个参数是要显示fragment
     * */
    private void showFragment(int fragment_container_id,Fragment fragment){
         FragmentTransaction transaction;
         transaction= mManager.beginTransaction();        //开启事务
         transaction.replace(fragment_container_id, fragment);
         transaction.commit();//提交事务
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}