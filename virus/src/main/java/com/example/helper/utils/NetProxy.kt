package com.example.helper.utils

import com.ConfigManager
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.net.URI.create


class NetProxy {
    //全局的一个代理
    companion object{
        private val mOkHttpClient = OkHttpClient.Builder().build()
        //供给下面的方法调用，好处是全局只有一个OkHttpClient对象
        fun doRequest(request:Request,callback: Callback) {
            val call = mOkHttpClient.newCall(request)
            call.enqueue(callback)
        }

        //发送命令 用户自定义数据：json、string、二进制数据（小于64K）
        //qos: 0 最多发送一次 1 至少发送一次，如果设备收到命令后没有应答，则会在下一次设备登录时若命令在有效期内（有效期定义参见timeout参数）则会重发该命令
        //timeout:命令有效时间，默认0
        //0：在线命令，若设备在线,下发给设备，若设备离线，直接丢弃
        //>0： 离线命令，若设备在线，下发给设备，若设备离线，在当前时间加timeout时间内为有效期，有效期内，若设备上线，则下发给设备
        //单位：秒
        fun sendCMD( device_id:String,qos:Int,timeout:Int,CMD:String,callback: Callback) {
            var url = ConfigManager.base.cmds_url ;
            url += "?";
            url += "device_id=" + device_id;
            url += "&qos=" + qos.toString();
            url += "&timeout=" + timeout.toString();
            val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()
            val body: RequestBody = RequestBody.create(JSON, "{  \"errno\": 0}")
            val request = Request.Builder()
                    .url(url)
                    .method("POST",body)
                    .header("api-key",ConfigManager.base.master_key)
                    .build()
            doRequest(request,callback)
        }
    }

}