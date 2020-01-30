package com.example.helper.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;

public class ConfigManager {
    public static class base{
        public static String devices_url = "http://api.heclouds.com/devices";
        public static String master_key = "yVoa2=3t7c61aQOurpXYxSOZMls=";
        public static String device_id = "582307500";
    }
    public static class video{
        public static String master_key = "M0M1m2zdiVDhzQmBYp=RBuLlwxQ=";
        public static String device_id = "583012656";
        public static String channel_id = "1";
        public static String play_protocol = "0";
        public static String play_level = "3";
    }

    //以下代码由解析获得
    @Deprecated
    private final static String fileName = "config.xml";
    public static String getBaseConfig(Context context, String name){
        return getConfig(context, name, "base");
    };
    public static String getVideoConfig(Context context,String name){
        return getConfig(context, name, "video");
    }

    public static String getConfig(Context context,String name,String tag){
        AssetManager assetManager = context.getAssets();
        try (InputStream open = assetManager.open(fileName)) {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(open,"utf-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if(eventType == XmlPullParser.START_TAG && parser.getName().equals(tag)) {
                    eventType = parser.next();
                    while (eventType != XmlPullParser.END_TAG || parser.getName() != tag){
                        if (eventType == XmlPullParser.START_TAG && parser.getName().equals(name)) {
                            parser.next();
                            return parser.getText();
                        }
                        eventType = parser.next();
                    }
                    return "null";
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null";
    }
}
