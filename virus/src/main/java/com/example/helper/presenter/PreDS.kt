package com.example.helper.presenter

import android.util.Log
import com.example.helper.base.IModel.CallBack
import com.example.helper.beans.DataStreams
import com.example.helper.interfacee.model.IMDS
import com.example.helper.interfacee.presenter.IPDS
import com.example.helper.interfacee.view.IVDS
import com.example.helper.model.DSManager

class PreDS : IPDS {
    private var mView:IVDS? = null;
    private var mModel:IMDS = DSManager();

    override fun fresh() {
        mModel.getData("args", object : CallBack{
            override fun onFailure(err: String?) {
                Log.e("WANG",err)
            }

            override fun onSucces(res: String?) {
                val dp = DataStreams()
                if (res != null) {
                    dp.parseDataStreams(res)//填充数据点
                    if (isAttatchedView){
                        val mHandler = mView?.getmHandler()
                        mHandler!!.post(object :Runnable{
                            override fun run() {
                                mView?.fresh(dp)
                            }
                        }
                        )
                    }
                }
            }
        })
    }

    override fun disAttatchView() {
        mView = null
    }

    override fun isAttatchedView(): Boolean {
        return mView != null
    }

    override fun attatchView(view: IVDS?) {
        mView = view
    }

}