package com.atguigu.mybatis_plus_demo.controller;

import com.atguigu.mybatis_plus_demo.entity.*;
import com.atguigu.mybatis_plus_demo.mapper.CarMapper;
import com.atguigu.mybatis_plus_demo.mapper.ChatMapper;
import com.atguigu.mybatis_plus_demo.mapper.UserMapper;
import com.atguigu.mybatis_plus_demo.service.CarService;
import com.atguigu.mybatis_plus_demo.service.ChatService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Resource
    private ChatMapper chatMapper;
    @Resource
    private UserMapper userMapper;
//    查
    @PostMapping("/listUser")
    @ResponseBody
    public List<Chat> getUser(@RequestBody Map<String, Object> data) {
        int uid1 = (int)data.get("uid1");
        int uid2 = (int)data.get("uid2");
        LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(3);
        QueryWrapper<Chat> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(wrapper -> wrapper
                        .eq("uid1", uid1)
                        .eq("uid2", uid2))
                .or(wrapper -> wrapper
                        .eq("uid1", uid2)
                        .eq("uid2", uid1))
                .ge("chattime", threeDaysAgo)
                .orderByAsc("chattime");
        return chatMapper.selectList(queryWrapper);
//        Map<String,Object> map = new HashMap<>();
//        map.put("uid1", uid1);
//        map.put("uid2", uid2);
//        return chatMapper.selectByMap(map);
    }
//    查 新信息(1v1)
    @PostMapping("/listNew")
    @ResponseBody
    public List<User> getNew(@RequestBody Map<String, Object> data) {
//        uid1：当前用户
        int uid1 = (int) data.get("uid1");

        QueryWrapper<Chat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid2", uid1)
                .eq("chatnew", '0')
                .select("uid1","uid2", "MAX(chatcontent) as chatcontent")  // 使用 MAX 聚合函数
                .groupBy("uid1","uid2");
        List<Chat> chats = chatMapper.selectList(queryWrapper);
        List<User> result = new ArrayList<>();
        for (Chat chat : chats) {
            User user = userMapper.selectById(chat.getUid1());

            User dto = new User();
            dto.setUid(user.getUid());
            dto.setUavatar(user.getUavatar());
            dto.setUname(user.getUname());
            result.add(dto);
        }
        return result;
    }
//    增
    @PostMapping("/add")
    @ResponseBody
    public int add(@RequestBody Chat data) {
        Chat ct = new Chat();
        ct.setUid1(data.getUid1());
        ct.setUid2(data.getUid2());
        ct.setChatcontent(data.getChatcontent());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        ct.setChattime(now);
        ct.setChatnew((char)data.getChatnew());
        int result = chatMapper.insert(ct);
        return result;
    }
//    改 已读
    @PostMapping("/update")
    @ResponseBody
    public int update(@RequestBody Chat data) {
//        Chat ct = new Chat();
//        ct.setUid1(data.getUid1());
//        ct.setUid2(data.getUid2());
//        ct.setChatcontent(data.getChatcontent());
//        Timestamp now = new Timestamp(System.currentTimeMillis());
//        ct.setChattime(now);
//        ct.setChatnew(data.getChatnew());
//        int result = chatMapper.insert(ct);
//        return result;
        int result = chatService.updateNew(data);
        return result;
    }
//    改 全部已读
    @PostMapping("/updateAll")
    @ResponseBody
    public int updateAll(@RequestBody Map<String, Object> data) {
        int uid1 = (int)data.get("uid1");
//        QueryWrapper<Chat> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("uid1", uid1)
//                .eq("chatnew", "0");
//        Chat chat = new Chat();
//        chat.setChatnew('1');
//        return chatMapper.update(chat, queryWrapper);
        UpdateWrapper<Chat> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("chatnew", '1')
                .eq("uid1", uid1)
                .eq("chatnew", '0');
        return chatMapper.update(null, updateWrapper);
    }


}
