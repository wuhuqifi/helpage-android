package com.example.helper.beans

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList

class DataStreams {
    private var mList: ArrayList<DataPoint> = ArrayList()
    fun parseDataStreams(JSON:String){
        val jo = JSONObject(JSON)
        val data: JSONArray? = jo.getJSONArray("data")
        if (data != null) {
            for (i in 0 until data.length()) {
                val dp = data.getJSONObject(i)
                mList
                var dataPoint = DataPoint()
                dataPoint.parseDataPoint(dp)
                mList.add(dataPoint)
            }
        }
    }
    fun getList(): ArrayList<DataPoint> {return mList}

}