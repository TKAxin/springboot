package com.xpx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpx.mapper.QuarantineSitesMapper;
import com.xpx.service.IQuarantineSitesService;
import com.xpx.entity.QuarantineSites;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-27
 */
@Service
public class QuarantineSitesServiceImpl extends ServiceImpl<QuarantineSitesMapper, QuarantineSites> implements IQuarantineSitesService {
    @Autowired
    private QuarantineSitesMapper quarantineSitesMapper;

    @Override
    public IPage<QuarantineSites> getPage(int current, int pageSize, QuarantineSites quarantineSites) {
    IPage<QuarantineSites> iPage = new Page<>(current,pageSize);
    LambdaQueryWrapper<QuarantineSites> lqw = new LambdaQueryWrapper<>();
    lqw.like(Strings.isNotEmpty(quarantineSites.getNumber()), QuarantineSites::getNumber,quarantineSites.getNumber());
    lqw.like(Strings.isNotEmpty(quarantineSites.getPhone()), QuarantineSites::getPhone,quarantineSites.getPhone());
    lqw.like(Strings.isNotEmpty(quarantineSites.getAddress()), QuarantineSites::getAddress,quarantineSites.getAddress());
    IPage<QuarantineSites> page = quarantineSitesMapper.selectPage(iPage,lqw);
    return page;
    }
}
