package com.xpx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xpx.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-04-03
 */
public interface ISysRoleService extends IService<SysRole> {
    IPage<SysRole> getPage(int current, int pageSize, SysRole sysRole);

    void setRoleMenu(Integer roleId, List<Integer> menuIds);

    List<Integer> getRoleMenu(Integer roleId);
}
