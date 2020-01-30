package com.example.helper.utils

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

class NetProxy {
    //全局的一个代理
    companion object{
        private val mOkHttpClient = OkHttpClient.Builder().build()
        fun doRequest(request:Request,callback: Callback) {
            val call = mOkHttpClient.newCall(request)
            call.enqueue(callback)
        }
    }

}