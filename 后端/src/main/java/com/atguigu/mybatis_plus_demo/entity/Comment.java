package com.atguigu.mybatis_plus_demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("comments")
public class Comment {
    @TableId(type = IdType.AUTO)
    @TableField("cid")
    private int cid;
    @TableField("sid")
    private int sid;
    @TableField("uid")
    private int uid;
    @TableField("suid")
    private int suid;
    @TableField("cContent")
    private String ccontent;
    @TableField("cTime")
    private Date ctime;
    @TableField("isDelete")
    private char isdelete;
}
