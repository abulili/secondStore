package com.atguigu.mybatis_plus_demo.service.Impl;

import com.atguigu.mybatis_plus_demo.entity.Car;
import com.atguigu.mybatis_plus_demo.entity.Chat;
import com.atguigu.mybatis_plus_demo.mapper.CarMapper;
import com.atguigu.mybatis_plus_demo.mapper.ChatMapper;
import com.atguigu.mybatis_plus_demo.service.CarService;
import com.atguigu.mybatis_plus_demo.service.ChatService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl  extends ServiceImpl<ChatMapper, Chat> implements ChatService {
    @Override
    public int updateNew(Chat data) {
        UpdateWrapper<Chat> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("uid1",data.getUid1())
                .eq("uid2",data.getUid2())
                .eq("chatnew",'0');
        Chat ct = new Chat();
        ct.setUid1(data.getUid1());
        ct.setUid2(data.getUid2());
        ct.setChattime(data.getChattime());
        ct.setChatcontent(data.getChatcontent());
        ct.setChatnew('1');
        int result = baseMapper.update(ct, updateWrapper);
        return result;
    }
    @Override
    public List<Chat> getChatMessagesByUid(int uid1, int uid2, int uid22,int uid11) {
        return baseMapper.selectChatMessagesByUid(uid1, uid2,uid22,uid11);
    }
}
