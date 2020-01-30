package com.example.helper.beans;

import org.json.JSONException;
import org.json.JSONObject;

public class DataPoint {
    private String id;
    private Object current_value;//String Int JsonObject
    private String update_at;
    String unit;
    String unit_symbol;
    DataPoint(String id){
        this.id = id;
        current_value = null;
        update_at =null;
    }
    public void parseDataPoint(String Json){
        try {
            JSONObject jsonObject = new JSONObject(Json);
            this.id = jsonObject.getString("id");
            this.update_at = jsonObject.getString("update_at");
            try {
                this.current_value = jsonObject.get("current_value");//可能找不到
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
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
        return current_value;
    }

    public void setCurrent_value(Object current_value) {
        this.current_value = current_value;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }
}
