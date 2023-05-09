package com.xpx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpx.entity.Passengers;
import com.xpx.controller.dto.UserDTO;
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
