package com.xpx.springboot.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpx.springboot.entity.SysMenu;
import com.xpx.springboot.mapper.SysMenuMapper;
import com.xpx.springboot.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.rmi.CORBA.Util;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-04-03
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findMenus(String name) {
        QueryWrapper<SysMenu> qw = new QueryWrapper<>();
        if(StrUtil.isNotBlank(name)){
            qw.like("name",name);
        }
        qw.orderByAsc("id");
        //查询所有数据
        List<SysMenu> list = list(qw);
        //查询pid为null的一级菜单
        List<SysMenu> parentMenus = list.stream().filter(sysMenu -> sysMenu.getPid() == null).collect(Collectors.toList());
        //找出一级菜单的子菜单
        for (SysMenu menu : parentMenus) {
            menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return parentMenus;
    }
}
