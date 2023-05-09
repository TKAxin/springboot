package com.xpx.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpx.springboot.entity.PasHealth;
import com.xpx.springboot.mapper.PasHealthMapper;
import com.xpx.springboot.service.IPasHealthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-04-01
 */
@Service
public class PasHealthServiceImpl extends ServiceImpl<PasHealthMapper, PasHealth> implements IPasHealthService {
    @Autowired
    private PasHealthMapper pasHealthMapper;

    @Override
    public IPage<PasHealth> getPage(int current, int pageSize, PasHealth pasHealth) {
    IPage<PasHealth> iPage = new Page<>(current,pageSize);
    LambdaQueryWrapper<PasHealth> lqw = new LambdaQueryWrapper<>();
    IPage<PasHealth> page = pasHealthMapper.selectPage(iPage,lqw);
    return page;
    }
}
