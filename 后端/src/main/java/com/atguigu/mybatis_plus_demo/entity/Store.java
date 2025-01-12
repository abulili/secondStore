package com.atguigu.mybatis_plus_demo.entity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("stores")
public class Store {
    @TableId(type = IdType.AUTO)
    @TableField("sid")
    private int sid;
    @TableField("sTitle")
    private String stitle;
    @TableField("sContent")
    private String scontent;
    @TableField("sNum")
    private int snum;
    @TableField("sUid")
    private int suid;
    @TableField("sTime")
    private Timestamp stime;
    @TableField("sPrice")
    private String sprice;
    @TableField("slng")
    private String slng;
    @TableField("slat")
    private String slat;
    @TableField("sPic")
    private String spic;
    @TableField("isDelete")
    private char isdelete;

}
