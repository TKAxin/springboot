package com.xpx.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpx.entity.Passengers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-31
 */
@Mapper
public interface PassengersMapper extends BaseMapper<Passengers> {

    Page<Passengers> getPasQs(Page<Passengers> iPage);

    Page<Passengers> getPasQsByName(Page<Passengers> iPage, String name);


    Page<Passengers> getPasHealth(Page<Passengers> iPage);

    Page<Passengers> getPasHealthByName(Page<Passengers> iPage, String name);

    @Select("SELECT COUNT(*) FROM `passengers`")
    int countpas();
}
