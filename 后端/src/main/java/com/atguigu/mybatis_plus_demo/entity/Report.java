package com.atguigu.mybatis_plus_demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("reports")
public class Report {
    @TableId(type = IdType.AUTO)
    @TableField("rid")
    private int rid;
    @TableField("isDeal")
    private char isdeal;
    @TableField("cid")
    private int cid;
    @TableField("sid")
    private int sid;
}
