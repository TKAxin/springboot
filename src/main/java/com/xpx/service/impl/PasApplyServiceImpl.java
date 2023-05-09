package com.xpx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpx.entity.PasApply;
import com.xpx.mapper.PasApplyMapper;
import com.xpx.service.IPasApplyService;
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
 * @since 2023-03-31
 */
@Service
public class PasApplyServiceImpl extends ServiceImpl<PasApplyMapper, PasApply> implements IPasApplyService {
    @Autowired
    private PasApplyMapper pasApplyMapper;

    @Override
    public IPage<PasApply> getPage(int current, int pageSize, PasApply pasApply) {
        IPage<PasApply> iPage = new Page<>(current,pageSize);
        LambdaQueryWrapper<PasApply> lqw = new LambdaQueryWrapper<>();
        lqw.isNotNull(PasApply::getStatus);
        lqw.orderByAsc(PasApply::getStatus);
        lqw.like(Strings.isNotEmpty(pasApply.getName()), PasApply::getName,pasApply.getName());
        lqw.like(Strings.isNotEmpty(pasApply.getPhone()),PasApply::getPhone,pasApply.getPhone());
        lqw.like(Strings.isNotEmpty(pasApply.getIdNumber()),PasApply::getIdNumber,pasApply.getIdNumber());
        IPage<PasApply> page = pasApplyMapper.selectPage(iPage,lqw);
    return page;
    }
}
