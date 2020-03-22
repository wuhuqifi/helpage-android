package com;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;

public class ConfigManager {

    public static class base{
        public static String product_id = "313858";
        public static String master_key = "BnmwMdeApAZcy1Hk=CvLrB71otg=";
        public static String device_id = "582307500";
        public static String api_key = "A13PYmB=V3jmt7cI2m9czRtpUmE=";

        public static String cmds_url = "https://api.heclouds.com/cmds";
        public static String datastream_url = "https://api.heclouds.com/devices/582307500/datastreams";
        public static String devices_url = "https://api.heclouds.com/devices";
        //登陆参数
    }
    public static class video{
        public static String master_key = "M0M1m2zdiVDhzQmBYp=RBuLIwxQ=";
        public static String device_id = "588169375";
        public static String channel_id = "1";

        public static String API_URL = "https://api.heclouds.com/ipc/video";
        public static String play_protocol = "0";//rtmp
        public static String play_level = "3";
    }

    //以下代码由解析获得 暂时没有到
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
