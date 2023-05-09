package com.xpx.springboot.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xpx.springboot.common.R;
import com.xpx.springboot.entity.QuarantineSites;
import com.xpx.springboot.service.IQuarantineSitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-27
 */
@RestController
@RequestMapping("/quarantine-sites")
public class QuarantineSitesController {
    @Autowired
    private IQuarantineSitesService quarantineSitesService;

    // 新增或者更新
    @PostMapping
    public R save(@RequestBody QuarantineSites quarantineSites) {
        quarantineSitesService.saveOrUpdate(quarantineSites);
        return R.success("添加或修改成功");
    }

    //根据ID删除
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        boolean removeById = quarantineSitesService.removeById(id);
        return R.success(removeById);
    }

    //批量删除
    @PostMapping("/delBatch")
    public R deleteBatch(@RequestBody List<Integer> ids){
        boolean removeByIds = quarantineSitesService.removeByIds(ids);
        return R.success(removeByIds);
    }

    @GetMapping("{current}/{pageSize}")
    public R<IPage> getPage(@PathVariable int current,
                            @PathVariable int pageSize,
                            QuarantineSites quarantineSites){
        IPage<QuarantineSites> page = quarantineSitesService.getPage(current, pageSize, quarantineSites);
        return R.success(page);
    }

    //
    @GetMapping
    public R findAll(){
        List<QuarantineSites> list = quarantineSitesService.list();
//        for (QuarantineSites quarantineSites : list) {
//            List<Integer> ids = new ArrayList<>();
//            ids.add(quarantineSites.getId());
//        }
        return R.success(list);
    }


}

