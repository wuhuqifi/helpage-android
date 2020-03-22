package com.example.helper.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helper.R;
import com.example.helper.base.BaseFragment;
import com.example.helper.beans.Device;
import com.example.helper.interfacee.view.IVDev;
import com.example.helper.interfacee.presenter.IPDev;
import com.example.helper.presenter.PreDev;
import com.example.helper.ui.activity.DevInfoActivity;


public class Fragment_device extends BaseFragment implements IVDev {
    private Device mDevice;//轮椅
    private IPDev mPreDev;//presenter
    private Handler mHandler;
    private ImageView mLogo;
    private TextView mName;
    private TextView mID;
    private TextView mState;

    @Override
    protected void initData() {
        //自动和UI主线程Looper关联
        mHandler = new Handler();
        mDevice = new Device("582307500","BnmwMdeApAZcy1Hk=CvLrB71otg=", "Oy9yltofYBvUWrh9");
        mPreDev = new PreDev(this);
        mPreDev.freshDev();
    }
    @Override
    public Device getDevice() {
        return mDevice;
    }
    @Override
    public Handler getHandler(){return mHandler;}
    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_device, container, false);
        View device = v.findViewById(R.id.dev_layout);//设备布局
        mName = device.findViewById(R.id.tv_dev_name);
        mID = device.findViewById(R.id.tv_dev_ID);
        mState = device.findViewById(R.id.tv_dev_state);
        mLogo = device.findViewById(R.id.iv_dev_img);//暂时作为刷新按钮
        return  v;
    }

    @Override
    protected void initListener() {
        mState.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), DevInfoActivity.class);
            startActivity(intent);
        });
        mLogo.setOnClickListener(v -> mPreDev.freshDev());//手动更新设备在线没
    }

    @Override
    public void fresh(String name, String ID, boolean isOnline) {
        mName.setText(name);
        mID.setText("ID:"+ID);
        if (isOnline) mState.setText("在线");
        else mState.setText("离线");
    }

//    private void buildGetServiceRequestBody(){
//       String devicesUrl = this.getResources().getString(R.string.devices_url);
//        String devicesNumDog = this.getResources().getString(R.string.device_dog_number);
//        String devicesNumCat = this.getResources().getString(R.string.device_cat_number);
//        mGetUrl.add(0,devicesUrl+ "/" +devicesNumDog);//分别构造Url：因为设备号不同
//        mGetUrl.add(1,devicesUrl+ "/" +devicesNumCat);
//        String api_key = this.getResources().getString(R.string.Master_api_key);
//        String header_name = "api-key";
//        String header_value = api_key;
//        mGetHeaders.add(0,new Headers.Builder().add(header_name,header_value).build());//get的头都是相同的，应为使用的是Master_api_key
//        mGetHeaders.add(1,new Headers.Builder().add(header_name,header_value).build());
//    }
//
//    private void buildPostServiceStreamRequestBody(String device_number,String StreamID){
//        //新增特定设备的数据流
//        String devicesUrl = this.getResources().getString(R.string.devices_url);
//        mPostUrl = devicesUrl + "/" + device_number +"/"+ "datastreams";
//        String api_key = this.getResources().getString(R.string.Master_api_key);
//        mPostHeaders = new Headers.Builder().add("api-key",api_key).build();
//        mJsonString = "{\n" +
//                "    \"id\": \""+StreamID+"\",\n" +
//                "    \"tags\": [\"null\"],\n" +
//                "    \"unit\": \"null\",\n" +
//                "    \"unit_symbol\": \"null\"\n" +
//                "}";
//
//    }
//
//
//    class getServiceTask extends AsyncTask<Object,Void,String>{
//         private int mServiceIndex = 0;//当前设备添加进mList就加1
//         @Override
//         protected String doInBackground(Object... urlAndHeaders) {
//             //传入对象数组，0号元素为String，1号元素为Headers
//             String s = MyHttp.getString((String) urlAndHeaders[0],(Headers) urlAndHeaders[1]);
//             return s;
//         }
//
//         @Override
//         protected void onProgressUpdate(Void... values) {
//             Log.d("wang", "onProgressUpdate!");
//         }
//
//         @Override
//         protected void onPostExecute(String s) {
//             try {
//                 //创建对象，数组元素是null
//                 ServiceInfo si = mServiceInfos[mServiceIndex] = new ServiceInfo();//得到当前设备的引用
//                 si.parseServiceJsonString(s);//解析Json字符串，放入si中\
//                 //
//                 si.setIconID(R.drawable.dog);
//
//                 mList.add(mServiceIndex,si);
//                 mServiceIndex++;//索引加1,不能在其他地方修改值
//                 if (mList.size() >= mServiceNum) {
//                     //等待数据加载完全
//                     //小bug，必须大于等于
//                     ServiceInfoAdapter sia = new ServiceInfoAdapter(getActivity(), mList);
//                     Log.d("wang", Thread.currentThread().getName()+"-->onPostExecute");
//                     mListView.setAdapter(sia);
//                 }
//
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//
//         }
//     }
//     class postServiceStreamTask extends AsyncTask<Object,Object,Object>{
//        //(String) objects[0] = url,(Headers) objects[1],(String) objects[2] = jsonString
//        @Override
//        protected Object doInBackground(Object... objects) {
//            String s = MyHttp.postString((String) objects[0],(Headers) objects[1],(String) objects[2]);
//            return s;
//        }
//
//        @Override
//        protected void onProgressUpdate(Object... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected void onPostExecute(Object o) {
//
//        }
//    }
}


