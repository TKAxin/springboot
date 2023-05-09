package com.xpx.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpx.springboot.controller.dto.UserDTO;
import com.xpx.springboot.entity.Passengers;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-31
 */
public interface IPassengersService extends IService<Passengers> {
    IPage<Passengers> getPage(int current, int pageSize, Passengers passengers);

    UserDTO login(UserDTO userDTO);

    Page<Passengers> getPasQs(Page<Passengers> iPage);

    Page<Passengers> getPasQsByName(Page<Passengers> iPage, String name);

    Page<Passengers> getPasHealth(Page<Passengers> iPage);

    Page<Passengers> getPasHealthByName(Page<Passengers> iPage, String name);
}
