package com.xpx.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xpx.springboot.entity.SysMenu;
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
public interface ISysMenuService extends IService<SysMenu> {
    List<SysMenu> findMenus(String name);
}
