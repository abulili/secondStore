package com.atguigu.mybatis_plus_demo.controller;

import com.atguigu.mybatis_plus_demo.entity.*;
import com.atguigu.mybatis_plus_demo.mapper.BoughtMapper;
import com.atguigu.mybatis_plus_demo.mapper.UserMapper;
import com.atguigu.mybatis_plus_demo.service.BoughtService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bought")
public class BoughtController {
    @Autowired
    private BoughtService boughtService;
    @Resource
    private BoughtMapper boughtMapper;
    @Resource
    private UserMapper userMapper;
//    查所有
//    查用户
//    查商品
    @PostMapping("/list")
    @ResponseBody
    public List<Bought> getList() {
        List<Bought> boughts = boughtMapper.selectList(null);
        return boughts;
    }
//    查这一单有没有
    @PostMapping("/listIsCom")
    @ResponseBody
    public List<Bought> listIsCom(@RequestBody Map<String, Object> data) {
        int sid = (int)data.get("iscom");
        Map<String,Object> map = new HashMap<>();
        map.put("iscom", sid);
         return boughtMapper.selectByMap(map);
    }
    //    卖家
    @PostMapping("/listUser")
    @ResponseBody
    public List<BoughtUserDTO> getUser(@RequestBody Map<String, Object> data) {
//        int uid1 = (int)data.get("uid1");
//        Map<String,Object> map = new HashMap<>();
//        map.put("uid1", uid1);
//        return boughtMapper.selectByMap(map);


        int uid1 = (int)data.get("uid1");
        List<Bought> boughts = boughtMapper.selectList(new QueryWrapper<Bought>().eq("uid1", uid1));

        List<BoughtUserDTO> result = new ArrayList<>();
        for (Bought bought : boughts) {
            User user = userMapper.selectById(bought.getUid2());

            BoughtUserDTO dto = new BoughtUserDTO();
            dto.setBought(bought);
            dto.setUser(user);

            result.add(dto);
        }
        return result;
    }
    //    商品
    @PostMapping("/listBought")
    @ResponseBody
    public List<Bought> getBought(@RequestBody Map<String, Object> data) {
        int sid = (int)data.get("sid");
        Map<String,Object> map = new HashMap<>();
        map.put("sid", sid);
        return boughtMapper.selectByMap(map);
    }
    @PostMapping("/add")
    @ResponseBody
    public int add(@RequestBody Bought data) {

        int orderid = data.getIscom();
        Map<String,Object> map = new HashMap<>();
        map.put("iscom", orderid);
        List<Bought> res = boughtMapper.selectByMap(map);
        if(res.size() == 0) {
            System.out.println(data);
            Bought bt = new Bought();
            bt.setSid(data.getSid());
            bt.setIscom(data.getIscom());
            bt.setUid2(data.getUid2());
            bt.setUid1(data.getUid1());
            bt.setMcontent(data.getMcontent());
            Timestamp now = new Timestamp(System.currentTimeMillis());
            bt.setMtime(now);
            bt.setIsdelete('0');
            int result = boughtMapper.insert(bt);
            return result;
        }
        return 0;
    }
    //    删
    @PostMapping("/del")
    @ResponseBody
    public int del(@RequestBody Bought data) {
        Bought bt = new Bought();
        bt.setMid(data.getMid());
        bt.setSid(data.getSid());
        bt.setIscom(data.getIscom());
        bt.setUid2(data.getUid2());
        bt.setUid1(data.getUid1());
        bt.setMcontent(data.getMcontent());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        bt.setMtime(now);
        bt.setIsdelete('1');
        int result = boughtMapper.updateById(bt);
        return result;
    }
}
