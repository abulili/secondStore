package com.atguigu.mybatis_plus_demo.entity;

import lombok.Data;

@Data
public class CommentUserDTO {
    private Comment comment;
    private User user;
}
