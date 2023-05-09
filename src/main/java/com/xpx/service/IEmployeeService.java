package com.xpx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xpx.entity.Employee;
import com.xpx.controller.dto.UserDTO;
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
