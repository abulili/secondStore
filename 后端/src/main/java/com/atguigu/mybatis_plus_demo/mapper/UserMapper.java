package com.atguigu.mybatis_plus_demo.mapper;

import com.atguigu.mybatis_plus_demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
