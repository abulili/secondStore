package com.atguigu.mybatis_plus_demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.Setter;

@Data
@TableName("car") // 加@TableName/TabkeField只是为了意义对应，免得改了没变
public class Car {
    @TableField("cid")
    @TableId(type = IdType.AUTO)
    private int cid;
    @TableField("uid")
    private int uid;
    @TableField("sid")
    private int sid;
    @TableField("sNum")
    private int snum;
//    @TableLogic //逻辑删除，即使执行delete，底层也为update
    @TableField("isDelete")
    private char isdelete;
}
