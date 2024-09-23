package com.atguigu.mybatis_plus_demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("stars")
public class Star {
    @TableId(type = IdType.AUTO)
    @TableField("lid")
    private int lid;
    @TableField("uid")
    private int uid;
    @TableField("sid")
    private int sid;
    @TableField("isDelete")
    private char isdelete;
}
