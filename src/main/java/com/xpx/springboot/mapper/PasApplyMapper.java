package com.xpx.springboot.mapper;

import com.xpx.springboot.entity.PasApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xpx.springboot.entity.PasQs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
public interface PasApplyMapper extends BaseMapper<PasApply> {

    @Update("UPDATE pas_apply SET pas_apply.`status` = 0 WHERE pas_apply.pas_id = #{id}")
    void updateStatusByPid(Integer id);
}
