package com.xpx.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpx.springboot.common.R;
import com.xpx.springboot.controller.dto.UserDTO;
import com.xpx.springboot.entity.Passengers;
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
 * @since 2023-03-31
 */
@RestController
@RequestMapping("/passengers")
public class PassengersController {
    @Autowired
    private IPassengersService passengersService;

    // 新增或者更新
    @PostMapping
    public R save(@RequestBody Passengers passengers) {
        passengersService.saveOrUpdate(passengers);
        return R.success("添加或修改成功");
    }

    //根据ID删除
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        boolean removeById = passengersService.removeById(id);
        return R.success(removeById);
    }

    //批量删除
    @PostMapping("/delBatch")
    public R deleteBatch(@RequestBody List<Integer> ids){
        boolean removeByIds = passengersService.removeByIds(ids);
        return R.success(removeByIds);
    }

    @GetMapping("{current}/{pageSize}")
    public R<IPage> getPage(@PathVariable int current,
                            @PathVariable int pageSize,
                            Passengers passengers){
        IPage<Passengers> page = passengersService.getPage(current, pageSize, passengers);
        return R.success(page);
    }

    @PostMapping("/login")
    //登录
    public R login(@RequestBody UserDTO userDTO){
        UserDTO dto = passengersService.login(userDTO);
        //3、如果没有查询到则返回登录失败结果
        if(dto == null){
            return R.error("账号错误");
        }
        //4、密码比对，如果不一致则返回登录失败结果
        if(!dto.getPassword().equals(userDTO.getPassword())){
            return R.error("密码错误");
        }
        return R.success(dto);
    }

    //注册
    @PostMapping("/register")
    public R register(@RequestBody UserDTO userDTO){
        Passengers passengers = new Passengers();
        passengers.setUsername(userDTO.getUsername());
        passengers.setPassword(userDTO.getPassword());
        passengersService.save(passengers);
        return R.success("注册成功");
    }
}

