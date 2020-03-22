package com.example.helper.beans;

import org.json.JSONException;
import org.json.JSONObject;

public class DataPoint {
    private String id;
    private String current_value;//String Int JsonObject
    private String update_at;
    String unit;
    String unit_symbol;
    public DataPoint(){
    }
    public void parseDataPoint(String Json){
        try {
            JSONObject jsonObject = new JSONObject(Json);
            parseDataPoint(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void parseDataPoint(JSONObject jsonObject){
        try {
            this.id = jsonObject.getString("id");
            this.update_at = jsonObject.getString("update_at");
            this.current_value = jsonObject.getString("current_value");//可能找不到
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getCurrent_value() {
        if (current_value == null) {
            return "null";
        }
        return current_value;
    }

    public void setCurrent_value(String current_value) {
        this.current_value = (String) current_value;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }
}
