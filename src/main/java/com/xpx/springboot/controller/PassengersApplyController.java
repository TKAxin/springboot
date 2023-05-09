package com.xpx.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpx.springboot.common.R;
import com.xpx.springboot.controller.dto.UserDTO;
import com.xpx.springboot.entity.Employee;
import com.xpx.springboot.entity.PasApply;
import com.xpx.springboot.entity.PasHealth;
import com.xpx.springboot.entity.Passengers;
import com.xpx.springboot.mapper.PassengersApplyMapper;
import com.xpx.springboot.service.IPasApplyService;
import com.xpx.springboot.service.IPasHealthService;
import com.xpx.springboot.service.IPassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端-》用户：申请隔离页面controller
 * 无实体类，单纯前端控制器
 */
@RestController
@RequestMapping("/passengers-apply")
public class PassengersApplyController {
    @Autowired
    private IPassengersService passengersService;

    @Autowired
    private IPasApplyService pasApplyService;

    @Autowired
    private PassengersApplyMapper passengersApplyMapper;


    /*
    * 用户页面
    * --------用户填报申请信息
    * 1. 特殊修改：用户申报时，通过申报数据的pusername对passengers表进行id查询，将此id作为pas_apply插入pas_id
    * */
    @PostMapping
    public R save(@RequestBody PasApply pasApply) {
        pasApply.setStatus("0");
        LambdaQueryWrapper<PasApply> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PasApply::getIdNumber,pasApply.getIdNumber());
        PasApply one = pasApplyService.getOne(lqw);
        if (one != null){
            pasApplyService.update(pasApply,lqw);
        }else {
            LambdaQueryWrapper<Passengers> lqw2 = new LambdaQueryWrapper<>();
            lqw2.eq(Passengers::getUsername,pasApply.getPusername());
            Passengers passengers = passengersService.getOne(lqw2);
            pasApply.setPasId(passengers.getId());
            pasApplyService.save(pasApply);
        }
        return R.success("添加或修改成功");
    }

    /*
     * 用户页面：申请隔离页面
     * --------用户查看申请信息
     * */
    @GetMapping("/{pusername}")
    public R getByUserName(@PathVariable String pusername) {
        LambdaQueryWrapper<PasApply> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PasApply::getPusername,pusername);
        List<PasApply> list = pasApplyService.list(lqw);
        return R.success(list);
    }

    /*
     * 用户页面：申请隔离页面
     * --------用户根据ID删除申请信息
     * */

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        boolean removeById = pasApplyService.removeById(id);
        return R.success(removeById);
    }

    /*
     * 用户页面：查询分配信息页面
     * --------用户查询分配信息
     * */
    @GetMapping("/pasAssign/{pusername}")
    public R getByUserName2(@PathVariable String pusername) {
        List<Passengers> assign = passengersApplyMapper.getAssign(pusername);
        return R.success(assign);
    }

    /*
     *   用户页面：个人信息页面
     *   1. 用户查询个人信息功能，通过账号查询
     * */
    @GetMapping("/findAll/{username}")
    public R getPerson(@PathVariable String username){
        QueryWrapper<Passengers> qw = new QueryWrapper<>();
        qw.eq("username",username);
        Passengers one = passengersService.getOne(qw);
        return R.success(one);
    }

    /*
     *   用户页面：个人信息页面
     *   1. 用户查询保存个人信息功能
     * */
    @PostMapping("/savePerson")
    public R savePerson(@RequestBody Passengers passengers){
        passengersService.updateById(passengers);
        return R.success("修改成功");
    }

    /*
     * 用户页面：每日申报健康信息页面
     * --------用户查看申请信息
     * */
    @GetMapping("/getHealth/{pusername}")
    public R getHealth(@PathVariable String pusername) {
        List<Passengers> assign = passengersApplyMapper.getHealth(pusername);
        return R.success(assign);
    }

    /*
     * 用户页面：每日申报健康信息页面
     * --------用户申报健康信息
     * */
    @Autowired
    private IPasHealthService pasHealthService;

    @PostMapping("/saveHealth")
    public R saveHealth(@RequestBody PasHealth pasHealth) {
        LambdaQueryWrapper<PasHealth> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PasHealth::getPasId,pasHealth.getPasId());
        PasHealth one = pasHealthService.getOne(lqw);
        if (one != null){
            pasHealthService.update(pasHealth,lqw);
            return R.success("修改成功");
        }
        return R.error("修改失败");
    }

}

