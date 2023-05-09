package com.xpx.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpx.springboot.entity.RoleMenu;
import com.xpx.springboot.entity.SysRole;
import com.xpx.springboot.mapper.RoleMenuMapper;
import com.xpx.springboot.mapper.SysRoleMapper;
import com.xpx.springboot.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-04-03
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public IPage<SysRole> getPage(int current, int pageSize, SysRole sysRole) {
    IPage<SysRole> iPage = new Page<>(current,pageSize);
    LambdaQueryWrapper<SysRole> lqw = new LambdaQueryWrapper<>();
    lqw.like(Strings.isNotEmpty(sysRole.getName()),SysRole::getName,sysRole.getName());
    IPage<SysRole> page = sysRoleMapper.selectPage(iPage,lqw);
    return page;
    }

    /**
     *
     * @param roleId
     * @param menuIds
     */
    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Transactional
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
        //先删除当前角色的roleId绑定的所有关系
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        roleMenuMapper.delete(queryWrapper);

        //删除完成之后，再把前端传进来的（菜单id：menuIds）绑定到当前角色id上
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setRoleId(roleId);
        for (Integer menuId : menuIds) {
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }


    @Override
    public List<Integer> getRoleMenu(Integer roleId) {
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);
        return menuIds;
    }
}
