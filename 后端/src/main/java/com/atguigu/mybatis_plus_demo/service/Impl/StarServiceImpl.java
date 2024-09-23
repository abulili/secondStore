package com.atguigu.mybatis_plus_demo.service.Impl;

import com.atguigu.mybatis_plus_demo.entity.Car;
import com.atguigu.mybatis_plus_demo.entity.Star;
import com.atguigu.mybatis_plus_demo.mapper.CarMapper;
import com.atguigu.mybatis_plus_demo.mapper.StarMapper;
import com.atguigu.mybatis_plus_demo.service.CarService;
import com.atguigu.mybatis_plus_demo.service.StarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StarServiceImpl extends ServiceImpl<StarMapper, Star> implements StarService {
}
