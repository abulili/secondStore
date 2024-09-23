package com.atguigu.mybatis_plus_demo.service.Impl;

import com.atguigu.mybatis_plus_demo.entity.Comment;
import com.atguigu.mybatis_plus_demo.entity.Order;
import com.atguigu.mybatis_plus_demo.mapper.CommentMapper;
import com.atguigu.mybatis_plus_demo.mapper.OrderMapper;
import com.atguigu.mybatis_plus_demo.service.CommentService;
import com.atguigu.mybatis_plus_demo.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl  extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
