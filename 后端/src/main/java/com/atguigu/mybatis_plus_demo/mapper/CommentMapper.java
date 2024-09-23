package com.atguigu.mybatis_plus_demo.mapper;

import com.atguigu.mybatis_plus_demo.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
