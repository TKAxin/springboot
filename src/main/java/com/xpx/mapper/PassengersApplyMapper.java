package com.xpx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xpx.entity.Passengers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface PassengersApplyMapper extends BaseMapper {

    List<Passengers> getAssign(String pusername);

    List<Passengers> getHealth(String pusername);
}
