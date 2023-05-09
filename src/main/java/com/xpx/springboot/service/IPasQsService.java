package com.xpx.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xpx.springboot.entity.PasQs;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
