package com.xpx.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xpx.springboot.entity.Passengers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;


@Mapper
public interface PassengersApplyMapper extends BaseMapper {

    List<Passengers> getAssign(String pusername);

    List<Passengers> getHealth(String pusername);
}
