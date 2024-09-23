package com.atguigu.mybatis_plus_demo.controller;

import com.atguigu.mybatis_plus_demo.entity.Car;
import com.atguigu.mybatis_plus_demo.entity.Star;
import com.atguigu.mybatis_plus_demo.entity.StarStoreDTO;
import com.atguigu.mybatis_plus_demo.entity.Store;
import com.atguigu.mybatis_plus_demo.mapper.CommentMapper;
import com.atguigu.mybatis_plus_demo.mapper.StarMapper;
import com.atguigu.mybatis_plus_demo.mapper.StoreMapper;
import com.atguigu.mybatis_plus_demo.service.CommentService;
import com.atguigu.mybatis_plus_demo.service.StarService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stars")
public class StarController {
    @Autowired
    private StarService starService;
    @Resource
    private StarMapper starMapper;
    @Resource
    private StoreMapper storeMapper;
//    查
//    增
//    删
    @PostMapping("/listUser")
    @ResponseBody
    public List<StarStoreDTO> getUser(@RequestBody Map<String, Object> data) {
        int uid = (int) data.get("uid");
//        Map<String,Object> map = new HashMap<>();
//        map.put("uid", uid);
//        map.put("isdelete", '0');
//        return starMapper.selectByMap(map);
        List<Star> stars = starMapper.selectList(new QueryWrapper<Star>().
                eq("uid",uid).
                eq("isdelete",'0'));
        List<StarStoreDTO> result = new ArrayList<>();
        for(Star star : stars) {
            Store store = storeMapper.selectById(star.getSid());
            StarStoreDTO dto = new StarStoreDTO();
            dto.setStar(star);
            dto.setStore(store);
            result.add(dto);
        }
        return result;

    }
//    详情页收藏没
@PostMapping("/storeStar")
@ResponseBody
public List<Star> storeStar(@RequestBody Map<String, Object> data) {
        System.out.println(data);
    int uid = (int) data.get("uid");
    int sid = (int) data.get("sid");
    Map<String,Object> map = new HashMap<>();
    map.put("uid", uid);
    map.put("sid", sid);
    map.put("isdelete", '0');
    return starMapper.selectByMap(map);
}
    @PostMapping("/add")
    @ResponseBody
    public int add(@RequestBody Star data) {
        System.out.println(data);
        Star sr = new Star();
        sr.setUid(data.getUid());
        sr.setSid(data.getSid());
        sr.setIsdelete('0');
        int result = starMapper.insert(sr);
        return result;
    }
    @PostMapping("/del")
    @ResponseBody
    public int del(@RequestBody Star data) {
        System.out.println(data);
        Star sr = new Star();
        sr.setLid(data.getLid());
        sr.setUid(data.getUid());
        sr.setSid(data.getSid());
        sr.setIsdelete('1');
        int result = starMapper.updateById(sr);
        return result;
    }
}
