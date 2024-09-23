package com.atguigu.mybatis_plus_demo.config;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Map;


public class MapConfig {

    public static void main(String[] args) {
        String ak = "njA0KpLRkXkbLX5x36Gsa648tBBnWjpW";
        String address = "天津之眼摩天轮";
        String httpUrl = "http://api.map.baidu.com/geocoding/v3/?address=" + address + "&output=json&ak=" + ak;
        StringBuilder json = new StringBuilder();
        try {
            URL url = new URL(httpUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        System.out.println(json.toString());
        String data = json.toString();
    }
}