package com.xpx.springboot.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xpx.springboot.common.R;
import com.xpx.springboot.entity.SysRole;
import com.xpx.springboot.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-04-03
 */

//权限功能：角色表

@RestController
@RequestMapping("/role")
public class SysRoleController {
    @Autowired
    private ISysRoleService sysRoleService;

    // 新增或者更新
    @PostMapping
    public R save(@RequestBody SysRole sysRole) {
        sysRoleService.saveOrUpdate(sysRole);
        return R.success("添加或修改成功");
    }

    //根据ID删除
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        boolean removeById = sysRoleService.removeById(id);
        return R.success(removeById);
    }

    @GetMapping
    public R findAll() {
        return R.success(sysRoleService.list());
    }

    @GetMapping("/{id}")
    public R findOne(@PathVariable Integer id) {
        return R.success(sysRoleService.getById(id));
    }

    //批量删除
    @PostMapping("/delBatch")
    public R deleteBatch(@RequestBody List<Integer> ids){
        boolean removeByIds = sysRoleService.removeByIds(ids);
        return R.success(removeByIds);
    }

    @GetMapping("/page/{current}/{pageSize}")
    public R<IPage> getPage(@PathVariable int current,
                            @PathVariable int pageSize,
                            SysRole sysRole){
        IPage<SysRole> page = sysRoleService.getPage(current, pageSize, sysRole);
        return R.success(page);
    }


    /**
     *
     * 绑定角色和菜单的关系
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    @PostMapping("/roleMenu/{roleId}")
    public R roleMenu(@PathVariable Integer roleId,@RequestBody List<Integer> menuIds){
        sysRoleService.setRoleMenu(roleId,menuIds);
        return R.success("角色绑定菜单成功");
    }

    /**
     * 查询roleId所对应的menuId数据，并且返回数据到前端，让树形控件能够显示出当前角色绑定了那些菜单
     *
     * @param roleId
     * @return
     */
    @GetMapping("/getRoleMenu/{roleId}")
    public R getRoleMenu(@PathVariable Integer roleId){
        List<Integer> roleMenu = sysRoleService.getRoleMenu(roleId);
        return R.success(roleMenu);
    }

}

