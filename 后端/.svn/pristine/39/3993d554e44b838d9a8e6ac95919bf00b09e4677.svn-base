package com.atguigu.mybatis_plus_demo.controller;

import com.atguigu.mybatis_plus_demo.entity.Comment;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/map")
public class MapController {
    @PostMapping("/getL")
    @ResponseBody
    public String getL(@RequestBody Map<String, Object> data) {
        String address = (String)data.get("address");
        String ak = "njA0KpLRkXkbLX5x36Gsa648tBBnWjpW";
        String httpUrl = "http://api.map.baidu.com/geocoding/v3/?address="+address+"&output=json&ak=" + ak;
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
        String res = json.toString();
        return res;
    }
    @PostMapping("/getR")
    @ResponseBody
    public String getR(@RequestBody Map<String, Object> data) {
        String lat = (String)data.get("lat");
        String lng = (String)data.get("lng");
        String ak = "njA0KpLRkXkbLX5x36Gsa648tBBnWjpW";
        String httpUrl = "https://api.map.baidu.com/reverse_geocoding/v3/?ak=" + ak + "&output=json&coordtype=wgs84ll&location=" + lng + ","+lat;
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
        String res = json.toString();
        return res;
    }
}
