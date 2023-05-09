package com.xpx.springboot.mapper;

import com.xpx.springboot.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-04-03
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("select id from sys_role where flag = #{role}")
    Integer selectRoleIdByFlag(String role);
}
