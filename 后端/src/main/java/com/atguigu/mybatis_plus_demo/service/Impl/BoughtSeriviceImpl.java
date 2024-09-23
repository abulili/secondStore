package com.atguigu.mybatis_plus_demo.service.Impl;

import com.atguigu.mybatis_plus_demo.entity.Bought;
import com.atguigu.mybatis_plus_demo.entity.Car;
import com.atguigu.mybatis_plus_demo.mapper.BoughtMapper;
import com.atguigu.mybatis_plus_demo.mapper.CarMapper;
import com.atguigu.mybatis_plus_demo.service.BoughtService;
import com.atguigu.mybatis_plus_demo.service.CarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BoughtSeriviceImpl  extends ServiceImpl<BoughtMapper, Bought> implements BoughtService {
}
