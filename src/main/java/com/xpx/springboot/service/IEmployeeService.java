package com.xpx.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xpx.springboot.controller.dto.UserDTO;
import com.xpx.springboot.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-27
 */
public interface IEmployeeService extends IService<Employee> {
    IPage<Employee> getPage(int current, int pageSize, Employee employee);

    UserDTO login(UserDTO userDTO);
}
