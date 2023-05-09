package com.xpx.springboot.mapper;

import com.xpx.springboot.entity.PasQs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-31
 */
@Mapper
public interface PasQsMapper extends BaseMapper<PasQs> {
    @Select("select * from pas_qs where pas_id=#{pid}")
    List<PasQs> selectByPID(Integer pid);
}
