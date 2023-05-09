package com.xpx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xpx.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    //通过前端查询出来的角色信息，通过对比role表的flag查询出对应的角色ID
    @Select("select menu_id from sys_role_menu where role_id = #{roleId}")
    List<Integer> selectByRoleId(@Param("roleId") Integer roleId);

    //通过上述查询的角色ID，查询Role_Menu表，查询出对应ID所对应的menus
    @Select("SELECT menu_id FROM `sys_role_menu` WHERE role_id = #{roleId}")
    List<Integer> selectMenusByRoleId(Integer roleId);
}
