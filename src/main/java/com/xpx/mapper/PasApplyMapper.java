package com.xpx.mapper;

import com.xpx.entity.PasApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

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
