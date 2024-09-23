package com.atguigu.mybatis_plus_demo.mapper;

import com.atguigu.mybatis_plus_demo.entity.Car;
import com.atguigu.mybatis_plus_demo.entity.CarStoreDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarStoreMapper extends BaseMapper<Car> {

    @Select("SELECT c.cid as sidcar, c.snum,c.cid, s.sid as sidstore, s.stitle, s.scontent, " +
            "s.suid, s.stime, s.sprice, s.slng, s.slat, s.spic " +
            "FROM car c " +
            "JOIN stores s ON c.sid = s.sid " +
            "WHERE c.uid = #{uid} AND c.isdelete = '0'")
    List<CarStoreDTO> selectCarStoreDTO(@Param("uid") int uid);
}


