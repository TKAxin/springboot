package com.xpx.springboot.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpx.springboot.common.R;
import com.xpx.springboot.entity.PasHealth;
import com.xpx.springboot.entity.Passengers;
import com.xpx.springboot.mapper.PassengersMapper;
import com.xpx.springboot.service.IPasHealthService;
import com.xpx.springboot.service.IPassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-04-01
 */
@RestController
@RequestMapping("/pas-health")
public class PasHealthController {
    @Autowired
    private IPasHealthService pasHealthService;

    @Autowired
    private IPassengersService passengersService;
    private PassengersMapper passengersMapper;

    // 新增或者更新
    @PostMapping
    public R save(@RequestBody PasHealth pasHealth) {

        pasHealthService.saveOrUpdate(pasHealth);
        return R.success("添加或修改成功");
    }

    //根据ID删除
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        boolean removeById = pasHealthService.removeById(id);
        return R.success(removeById);
    }

    //批量删除
    @PostMapping("/delBatch")
    public R deleteBatch(@RequestBody List<Integer> ids){
        boolean removeByIds = pasHealthService.removeByIds(ids);
        return R.success(removeByIds);
    }

    @GetMapping("{current}/{pageSize}")
    public R<IPage> getPage(@PathVariable int current,
                            @PathVariable int pageSize,
                            PasHealth pasHealth){
        IPage<PasHealth> page = pasHealthService.getPage(current, pageSize, pasHealth);
        return R.success(page);
    }

    /*
     * 管理员页面
     * 1.查询passengers表和pas_health表
     * 2.输出到 旅客隔离情况页面
     * */
    @GetMapping("/pagePasHealth/{current}/{pageSize}")
    public Page<Passengers> pagePasHealth(@PathVariable Integer current,
                                          @PathVariable Integer pageSize){
        Page<Passengers> iPage = new Page<>(current, pageSize);
        return passengersService.getPasHealth(iPage);
    }

    @GetMapping("/pagePasHealthByName/{current}/{pageSize}/{name}")
    public Page<Passengers> pagePasHealthByName(@PathVariable Integer current,
                                                @PathVariable Integer pageSize,
                                                @PathVariable String name){
        Page<Passengers> iPage = new Page<>(current, pageSize);
        return passengersService.getPasHealthByName(iPage,name);
    }

}

