package com.atguigu.mybatis_plus_demo.mapper;

import com.atguigu.mybatis_plus_demo.entity.CarStoreDTO;
import com.atguigu.mybatis_plus_demo.entity.Chat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMapper extends BaseMapper<Chat> {
    @Select("SELECT * FROM chat WHERE uid1 = #{uid1} AND uid2 = #{uid2} OR uid1 = #{uid22} AND uid2 = #{uid11} ORDER BY chattime")
    List<Chat> selectChatMessagesByUid(@Param("uid1") int uid1, @Param("uid2") int uid2,@Param("uid22") int uid22,@Param("uid11") int uid11);


}
