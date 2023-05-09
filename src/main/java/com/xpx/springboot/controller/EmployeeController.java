package com.xpx.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xpx.springboot.common.R;
import com.xpx.springboot.controller.dto.UserDTO;
import com.xpx.springboot.entity.Employee;
import com.xpx.springboot.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-27
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping()
    public R findAll() {
        List<Employee> list = employeeService.list();
        return R.success(list);
    }

    // 新增或者更新
    @PostMapping
    public R save(@RequestBody Employee employee) {
        employeeService.saveOrUpdate(employee);
        return R.success("添加或修改成功");
    }

    //根据ID删除
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        boolean removeById = employeeService.removeById(id);
        return R.success(removeById);
    }

    //批量删除
    @PostMapping("/delBatch")
    public R deleteBatch(@RequestBody List<Integer> ids){
        boolean removeByIds = employeeService.removeByIds(ids);
        return R.success(removeByIds);
    }

    @GetMapping("{current}/{pageSize}")
    public R<IPage> getPage(@PathVariable int current,
                            @PathVariable int pageSize,
                            Employee employee){
        IPage<Employee> page = employeeService.getPage(current, pageSize, employee);
        return R.success(page);
    }

    @PostMapping("/login")
    //登录
    public R login(@RequestBody UserDTO userDTO){
        UserDTO dto = employeeService.login(userDTO);
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

    //通过用户名查询，将数据展现在个人信息页面
    @GetMapping("/username/{username}")
    public R selectById(@PathVariable String username){
        QueryWrapper<Employee> qw = new QueryWrapper<>();
        qw.eq("username",username);
        Employee one = employeeService.getOne(qw);
        return R.success(one);
    }

}

