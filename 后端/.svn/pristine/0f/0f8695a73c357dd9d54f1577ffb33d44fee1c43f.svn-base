package com.atguigu.mybatis_plus_demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("users")
public class User {
    @TableId(type = IdType.AUTO)
    @TableField("uid")
    private int uid;
    @TableField("uName")
    private String uname;
    @TableField("uAvatar")
    private String uavatar;
    @TableField("uEmail")
    private String uemail;
    @TableField("uPhone")
    private String uphone;
    @TableField("uPwd")
    private String upwd;
    @TableField("isReal")
    private char isreal;
    @TableField("salt")
    private String salt;
    @TableField("uKill")
    private int ukill;
    @TableField("uAdmin")
    private String uadmin;
    @TableField("uInitTime")
    private Date uinittime;
    @TableField("isDelete")
    private char isdelete;
}
