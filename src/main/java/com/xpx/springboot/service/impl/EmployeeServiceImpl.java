package com.xpx.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpx.springboot.controller.dto.UserDTO;
import com.xpx.springboot.entity.Employee;
import com.xpx.springboot.entity.SysMenu;
import com.xpx.springboot.mapper.EmployeeMapper;
import com.xpx.springboot.mapper.RoleMenuMapper;
import com.xpx.springboot.mapper.SysMenuMapper;
import com.xpx.springboot.mapper.SysRoleMapper;
import com.xpx.springboot.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xpx.springboot.service.ISysMenuService;
import com.xpx.springboot.utils.TokenUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-27
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private ISysMenuService menuService;

    @Override
    public IPage<Employee> getPage(int current, int pageSize, Employee employee) {
        IPage<Employee> iPage = new Page<>(current,pageSize);
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(employee.getName()),Employee::getName,employee.getName());
        lqw.like(Strings.isNotEmpty(employee.getPhone()),Employee::getPhone,employee.getPhone());
        lqw.like(Strings.isNotEmpty(employee.getIdNumber()),Employee::getIdNumber,employee.getIdNumber());
        IPage<Employee> page = employeeMapper.selectPage(iPage,lqw);
        return page;
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper();
        lqw.eq(Employee::getUsername,userDTO.getUsername());
        Employee one = employeeMapper.selectOne(lqw);
        if (one != null){
            //声明新的登录变量，防止修改传入的userDTO，通过返回新变量，在controller层进行错误判断
            UserDTO dto = new UserDTO();
            BeanUtil.copyProperties(one, dto, true);
            //设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            dto.setToken(token);

            String role = one.getRole(); // ROLE_ADMIN
            // 设置用户的菜单列表
            List<SysMenu> roleMenus = getRoleMenus(role);
            dto.setMenus(roleMenus);

            return dto;
        }else {
            return null;
        }
    }

    /**
     * 获取当前角色的菜单项
     * @param roleId
     * @return
     */
//    private List<SysMenu> getRoleMenus(String roleFlag) {
//        Integer roleId = roleMapper.selectRoleIdByFlag(roleFlag);
//        // 当前角色的所有菜单id集合
//        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);
//
//        // 查出系统所有的菜单(树形)
//        List<SysMenu> menus = menuService.findMenus("");
//        // new一个最后筛选完成之后的list
//        List<SysMenu> roleMenus = new ArrayList<>();
//        // 筛选当前用户角色的菜单
//        for (SysMenu menu : menus) {
//            if (menuIds.contains(menu.getId())) {
//                roleMenus.add(menu);
//            }
//            List<SysMenu> children = menu.getChildren();
//            // removeIf()  移除 children 里面不在 menuIds集合中的 元素
//            children.removeIf(child -> !menuIds.contains(child.getId()));
//        }
//        return roleMenus;
//    }
    private List<SysMenu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectRoleIdByFlag(roleFlag);
        // 当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);

        // 查出系统所有的菜单(树形)
        List<SysMenu> menus = menuService.findMenus("");
        // new一个最后筛选完成之后的list
        List<SysMenu> roleMenus = new ArrayList<>();
        // 筛选当前用户角色的菜单
        for (SysMenu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<SysMenu> children = menu.getChildren();
            // removeIf()  移除 children 里面不在 menuIds集合中的 元素
            boolean isSuccess = children.removeIf(child -> !menuIds.contains(child.getId()));
            if (children.size() != 0 && isSuccess) {
                roleMenus.add(menu);
            }
        }
        return roleMenus;
    }
}
