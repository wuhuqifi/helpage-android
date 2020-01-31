package com.example.helper.model

import com.example.helper.base.IModel
import com.example.helper.interfacee.model.IMDS
import com.example.helper.utils.ConfigManager
import com.example.helper.utils.NetProxy.Companion.doRequest
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class DSManager: IMDS{
    override fun getData(args: String?, callback: IModel.CallBack?) {
        val request = Request.Builder()
                .url(ConfigManager.base.datastream_url)
                .method("GET", null)
                //这里居然只能用api_key而不能用master_key
                .header("api-key", ConfigManager.base.api_key)
                .build()
        doRequest(request, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback?.onFailure("请求数据流失败")
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                callback?.onSucces(response.body!!.string())
            }
        })
    }
}