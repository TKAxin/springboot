package com.xpx.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xpx.springboot.common.Constants;
import com.xpx.springboot.common.R;
import com.xpx.springboot.entity.Dict;
import com.xpx.springboot.entity.SysMenu;
import com.xpx.springboot.mapper.DictMapper;
import com.xpx.springboot.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-04-03
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController {
    @Autowired
    private ISysMenuService sysMenuService;

    @Resource
    private DictMapper dictMapper;

    // 新增或者更新
    @PostMapping
    public R save(@RequestBody SysMenu sysMenu) {
        sysMenuService.saveOrUpdate(sysMenu);
        return R.success("添加或修改成功");
    }

    //根据ID删除
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        boolean removeById = sysMenuService.removeById(id);
        return R.success(removeById);
    }

    //批量删除
    @PostMapping("/delBatch")
    public R deleteBatch(@RequestBody List<Integer> ids){
        boolean removeByIds = sysMenuService.removeByIds(ids);
        return R.success(removeByIds);
    }

    @GetMapping()
    public R findAll(@RequestParam(defaultValue = "") String name){
        return R.success(sysMenuService.findMenus(name));
    }

    //获取icon图标信息
    @GetMapping("/icons")
    public R getIcons(){
        QueryWrapper<Dict> qw = new QueryWrapper<>();
        qw.eq("type", Constants.DICT_TYPE_ICON);
        return R.success(dictMapper.selectList(null));
    }

}

