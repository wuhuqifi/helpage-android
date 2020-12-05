package com.example.helper.ui.activity
import android.content.Context
import android.content.SharedPreferences
import android.view.WindowManager
import android.widget.Toast
import com.example.helper.R
import com.example.helper.base.BaseActivity
import com.ont.media.player.IjkVideoView
import java.util.*

class PlayActivity: BaseActivity() {
    //数据持久化
    private lateinit var mPlayParm:SharedPreferences
    //播放必要数据
    private var device_id: String = ""
    private var channel_id: String = ""
    private var video_master_key: String = ""
    //view
    private lateinit var mPlayView:IjkVideoView

    override fun setFullScreen() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
    override fun getLayoutID(): Int {
        return R.layout.activity_play
    }

    override fun initView() {
        mPlayView = findViewById(R.id.vv_player)

    }
    override fun initData() {
        mPlayParm = this.getSharedPreferences("playParm", Context.MODE_PRIVATE)
        if (!getSharedPer()) {
            //第一次没取到就去配置文件读
            var p = Properties()
            val inputStream = assets.open("config")
            p.load(inputStream)
            device_id =p.getProperty("device_id")
            channel_id =p.getProperty("channel_id")
            video_master_key = p.getProperty("video_master_key")
            saveSharedPer()//保存起来
            Toast.makeText(this, video_master_key, Toast.LENGTH_LONG).show()
        }
    }
    fun getSharedPer(): Boolean {
        device_id = mPlayParm.getString("device_id","").toString()
        channel_id = mPlayParm.getString("channel_id","").toString()
        video_master_key = mPlayParm.getString("video_master_key","").toString()
        if (video_master_key == "")
            //说明是第一次创建
            return false
        return true
    }
    fun saveSharedPer(){
        val edit = mPlayParm.edit()
        edit.putString("device_id",device_id)
        edit.putString("channel_id",channel_id)
        edit.putString("video_master_key",video_master_key)
        edit.commit()
    }
}