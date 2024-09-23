package com.atguigu.mybatis_plus_demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("bought")
public class Bought {
    @TableId(type = IdType.AUTO)
    @TableField("mid")
    private int mid;
    @TableField("sid")
    private int sid;
    @TableField("isCom")
    private int iscom;
    @TableField("uid2")
    private int uid2;
    @TableField("uid1")
    private int uid1;
    @TableField("mContent")
    private String mcontent;
    @TableField("mtime")
    private Date mtime;
    @TableField("isDelete")
    private char isdelete;
}
