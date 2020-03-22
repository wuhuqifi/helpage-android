package com.example.helper.beans;

import org.jetbrains.annotations.Contract;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Device {
    public String ID;
    public String apiKey;
    public String reg_code;
    public String name;//title
    public Boolean isOnline;
    public List<String> dataStreamsID = new ArrayList<>();
    public Device(String ID,String apiKey,String reg_code){
        this.ID = ID;
        this.apiKey = apiKey;
        this.reg_code = reg_code;
    };

    public void parseDeviseFromJSON(String JsonString) {
        try {
            JSONObject jo = new JSONObject(JsonString);
            JSONObject data = jo.getJSONObject("data");
            this.name = data.getString("title");
            this.isOnline = data.getBoolean("online");
            JSONArray dataStreams = data.getJSONArray("datastreams");
            for (int i = 0;i < dataStreams.length();i++){
                JSONObject dataPoint = dataStreams.getJSONObject(i);
                dataStreamsID.add(dataPoint.getString("id"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Device{" +
                "apiKey='" + apiKey + '\'' +
                ", ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", dataStreamsID=" + dataStreamsID +
                ", isOnline=" + isOnline +
                '}';
    }

}
