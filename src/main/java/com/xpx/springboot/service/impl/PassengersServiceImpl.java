package com.xpx.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpx.springboot.controller.dto.UserDTO;
import com.xpx.springboot.entity.Employee;
import com.xpx.springboot.entity.Passengers;
import com.xpx.springboot.mapper.PasApplyMapper;
import com.xpx.springboot.mapper.PassengersMapper;
import com.xpx.springboot.service.IPassengersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xpx.springboot.utils.TokenUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-31
 */
@Service
public class PassengersServiceImpl extends ServiceImpl<PassengersMapper, Passengers> implements IPassengersService {

    @Autowired
    private PassengersMapper passengersMapper;

    @Override
    public IPage<Passengers> getPage(int current, int pageSize, Passengers passengers) {
        IPage<Passengers> iPage = new Page<>(current,pageSize);
        LambdaQueryWrapper<Passengers> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(passengers.getName()),Passengers::getName,passengers.getName());
        lqw.like(Strings.isNotEmpty(passengers.getPhone()),Passengers::getPhone,passengers.getPhone());
        lqw.like(Strings.isNotEmpty(passengers.getIdNumber()),Passengers::getIdNumber,passengers.getIdNumber());
        IPage<Passengers> page = passengersMapper.selectPage(iPage,lqw);
        return page;
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        LambdaQueryWrapper<Passengers> lqw = new LambdaQueryWrapper();
        lqw.eq(Passengers::getUsername,userDTO.getUsername());
        Passengers one = passengersMapper.selectOne(lqw);
        if (one != null){
            //声明新的登录变量，防止修改传入的userDTO，通过返回新变量，在controller层进行错误判断
            UserDTO dto = new UserDTO();
            BeanUtil.copyProperties(one, dto, true);
            //设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            dto.setToken(token);
            return dto;
        }else {
            return null;
        }
    }


    /*
     * 分配隔离点页面查询
     * */

    @Override
    public Page<Passengers> getPasQs(Page<Passengers> iPage) {
        return passengersMapper.getPasQs(iPage);
    }

    @Override
    public Page<Passengers> getPasQsByName(Page<Passengers> iPage, String name) {
        return passengersMapper.getPasQsByName(iPage,name);
    }

    /*
    * 旅客隔离情况页面查询
    * */
    @Override
    public Page<Passengers> getPasHealth(Page<Passengers> iPage) {
        return passengersMapper.getPasHealth(iPage);
    }

    @Override
    public Page<Passengers> getPasHealthByName(Page<Passengers> iPage, String name) {
        return passengersMapper.getPasHealthByName(iPage,name);
    }

}
