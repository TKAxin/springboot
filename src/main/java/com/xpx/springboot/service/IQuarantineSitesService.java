package com.xpx.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xpx.springboot.entity.QuarantineSites;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-27
 */
public interface IQuarantineSitesService extends IService<QuarantineSites> {
    IPage<QuarantineSites> getPage(int current, int pageSize, QuarantineSites quarantineSites);
}
