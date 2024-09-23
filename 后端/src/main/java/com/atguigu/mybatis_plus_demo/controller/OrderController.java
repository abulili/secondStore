package com.atguigu.mybatis_plus_demo.controller;

import com.atguigu.mybatis_plus_demo.entity.*;
import com.atguigu.mybatis_plus_demo.mapper.OrderMapper;
import com.atguigu.mybatis_plus_demo.mapper.StoreMapper;
import com.atguigu.mybatis_plus_demo.mapper.UserMapper;
import com.atguigu.mybatis_plus_demo.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private StoreMapper storeMapper;
    @Resource
    private UserMapper userMapper;
//    查
    @PostMapping("/listSale")
    @ResponseBody
    public List<OrderStoreUserDTO> getList1(@RequestBody Map<String, Object> data) {
        int uid = (int) data.get("uid");
//        Map<String,Object> map = new HashMap<>();
//        map.put("uid", uid);
//        return orderMapper.selectByMap(map);
        List<Order> orders = orderMapper.selectList(new QueryWrapper<Order>().
                eq("uid", uid));

        List<OrderStoreUserDTO> result = new ArrayList<>();
        for (Order order : orders) {
            Store store = storeMapper.selectById(order.getSid());
            User user = userMapper.selectById(order.getUid2());
            OrderStoreUserDTO dto = new OrderStoreUserDTO();
            dto.setOrder(order);
            dto.setStore(store);
            dto.setUser(user);
            result.add(dto);
        }
        return result;
    }
    @PostMapping("/listBought")
    @ResponseBody
    public List<OrderStoreUserDTO> getList2(@RequestBody Map<String, Object> data) {
        int uid2 = (int) data.get("uid2");
//        Map<String,Object> map = new HashMap<>();
//        map.put("uid2", uid2);
//        return orderMapper.selectByMap(map);
        List<Order> orders = orderMapper.selectList(new QueryWrapper<Order>().
                eq("uid2", uid2));

        List<OrderStoreUserDTO> result = new ArrayList<>();
        for (Order order : orders) {
            Store store = storeMapper.selectById(order.getSid());
            User user = userMapper.selectById(order.getUid());
            OrderStoreUserDTO dto = new OrderStoreUserDTO();
            dto.setOrder(order);
            dto.setStore(store);
            dto.setUser(user);
            result.add(dto);
        }
        return result;
    }
//    增
    @PostMapping("/add")
    @ResponseBody
    public int add(@RequestBody Order data) {
        Order ct = new Order();
        ct.setUid(data.getUid());
        ct.setUid2(data.getUid2());
        ct.setSid(data.getSid());
        ct.setBnum(data.getBnum());
        ct.setBstatus("未付款");
        int result = orderMapper.insert(ct);
        return result;
    }
//    改
    @PostMapping("/update")
    @ResponseBody
    public int updateStatus(@RequestBody Order data) {
        System.out.println(data);
        Order ct = new Order();
        ct.setBid(data.getBid());
        ct.setUid(data.getUid());
        ct.setUid2(data.getUid2());
        ct.setSid(data.getSid());
        ct.setBnum(data.getBnum());
        ct.setBstatus(data.getBstatus());
        int result = orderMapper.updateById(ct);
        return result;
    }
}
