package com.example.helper.presenter;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.helper.beans.Device;
import com.example.helper.interfacee.model.IMDev;
import com.example.helper.interfacee.presenter.IPDev;
import com.example.helper.interfacee.view.IVDev;
import com.example.helper.model.DeviceManager;

public class PreDev implements IPDev {
    public final static int TYPE_FRESH = 1;
    private IVDev mView;
    private IMDev mModel;
    private Device mDevice;

    public PreDev(IVDev v){
        this.mView = v;
        mDevice = v.getDevice();
        mModel = new DeviceManager();
    }
    @Override
    public void freshDev() {
        mModel.getDevice(mDevice,new CallBack() {
            @Override
            public void onSucces(String data) {
                //非UI线程 解析数据因该防在这里
                parseDev(data);
                mView.getHandler().post(() -> {
                    mView.fresh(mDevice.name,mDevice.ID,mDevice.isOnline);
                });
            }

            @Override
            public void onFailure(String err) {
                //Toast.makeText(((Fragment)mView).getContext(),err,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void login() {
        mModel.login(mDevice, new CallBack() {
            @Override
            public void onSucces(String data) {
                Toast.makeText(((Fragment)mView).getContext(),"登陆成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String err) {
                Toast.makeText(((Fragment)mView).getContext(),"登陆失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void parseDev(String data) {
        mDevice.parseDeviseFromJSON(data);
    }
}
