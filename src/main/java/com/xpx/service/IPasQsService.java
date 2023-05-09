package com.xpx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xpx.entity.PasQs;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-31
 */
public interface IPasQsService extends IService<PasQs> {
    IPage<PasQs> getPage(int current, int pageSize, PasQs pasQs);

}
