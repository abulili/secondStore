package com.atguigu.mybatis_plus_demo.mapper;

import com.atguigu.mybatis_plus_demo.entity.Car;
import com.atguigu.mybatis_plus_demo.entity.CarStoreDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarMapper extends BaseMapper<Car> {
    @Select("SELECT c.cid as sidcar, c.snum, s.sid as sidstore, s.stitle, s.scontent, " +
            "s.suid, s.stime, s.sprice, s.slng, s.slat " +
            "FROM car c " +
            "JOIN stores s ON c.sid = s.sid " +
            "WHERE c.uid = 0 AND c.isdelete = '0'")
    List<CarStoreDTO> selectCarStoreDTO();
}
