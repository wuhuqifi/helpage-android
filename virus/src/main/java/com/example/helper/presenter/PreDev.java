package com.example.helper.presenter;

import android.content.Context;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.helper.beans.Device;
import com.example.helper.interfacee.model.IDevice;
import com.example.helper.interfacee.presenter.IPreDev;
import com.example.helper.interfacee.view.IFreshDev;
import com.example.helper.model.DeviceManager;
import com.example.helper.ui.fragments.Fragment_device;

public class PreDev implements IPreDev {
    public final static int TYPE_FRESH = 1;
    private IFreshDev mView;
    private IDevice mModel;
    private Device mDevice;

    public PreDev(IFreshDev v){
        this.mView = v;
        mDevice = v.getDevice();
    }
    @Override
    public void freshDev() {
        mModel = new DeviceManager();
        mModel.getDevice(mDevice,new CallBack() {
            @Override
            public void onSucces(String data) {
                //非UI线程
                parseDev(data);
                Message m = new Message();
                m.what = PreDev.TYPE_FRESH;//更新UI要求
                m.obj = mDevice;
                mView.getHandler().post(() -> {
                    mView.fresh(mDevice.name,mDevice.ID,mDevice.isOnline);
                });
            }

            @Override
            public void onFailure(String err) {
                Toast.makeText(((Fragment)mView).getContext(),err,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void parseDev(String data) {
        mDevice.parseDeviseFromJSON(data);
    }
}
