package com.xpx.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xpx.springboot.entity.PasApply;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-31
 */
public interface IPasApplyService extends IService<PasApply> {
    IPage<PasApply> getPage(int current, int pageSize, PasApply pasApply);
}
