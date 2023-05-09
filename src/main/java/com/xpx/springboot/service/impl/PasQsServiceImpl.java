package com.xpx.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpx.springboot.entity.PasQs;
import com.xpx.springboot.mapper.PasQsMapper;
import com.xpx.springboot.service.IPasQsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-31
 */
@Service
public class PasQsServiceImpl extends ServiceImpl<PasQsMapper, PasQs> implements IPasQsService {
    @Autowired
    private PasQsMapper pasQsMapper;

    @Override
    public IPage<PasQs> getPage(int current, int pageSize, PasQs pasQs) {
    IPage<PasQs> iPage = new Page<>(current,pageSize);
    LambdaQueryWrapper<PasQs> lqw = new LambdaQueryWrapper<>();
    IPage<PasQs> page = pasQsMapper.selectPage(iPage,lqw);
    return page;
    }


}
