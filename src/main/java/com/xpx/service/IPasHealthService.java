package com.xpx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xpx.entity.PasHealth;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-04-01
 */
public interface IPasHealthService extends IService<PasHealth> {
    IPage<PasHealth> getPage(int current, int pageSize, PasHealth pasHealth);
}
