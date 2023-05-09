package com.xpx.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xpx.common.R;
import com.xpx.entity.PasApply;
import com.xpx.entity.PasQs;
import com.xpx.service.IPasApplyService;
import com.xpx.service.IPasQsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-31
 */
@RestController
@RequestMapping("/pas-apply")
public class PasApplyController {
    @Autowired
    private IPasApplyService pasApplyService;

//    // 新增或者更新
//    @PostMapping
//    public R save(@RequestBody PasApply pasApply) {
//        pasApplyService.saveOrUpdate(pasApply);
//        return R.success("添加或修改成功");
//    }


    @Autowired
    private IPasQsService pasQsService;
    // 修改版：通过判断审批状态码进行对旅客隔离点分配表插入基本数据，为分配隔离点做铺垫
    @PostMapping
    public R save(@RequestBody PasApply pasApply) {
        String status = pasApply.getStatus();
        if (status.equals("1")){
            PasQs pasQs = new PasQs();
            pasQs.setPasId(pasApply.getPasId());
            pasQsService.save(pasQs);
        }else if (status.equals("0")){
            LambdaQueryWrapper<PasQs> lqw = new LambdaQueryWrapper<>();
            lqw.eq(PasQs::getPasId,pasApply.getPasId());
            pasQsService.remove(lqw);
        }
        pasApplyService.saveOrUpdate(pasApply);
        return R.success("添加或修改成功");
    }

    //根据ID删除
    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        boolean removeById = pasApplyService.removeById(id);
        return R.success(removeById);
    }

    //批量删除
    @PostMapping("/delBatch")
    public R deleteBatch(@RequestBody List<Integer> ids){
        boolean removeByIds = pasApplyService.removeByIds(ids);
        return R.success(removeByIds);
    }

    @GetMapping("{current}/{pageSize}")
    public R<IPage> getPage(@PathVariable int current,
                            @PathVariable int pageSize,
                            PasApply pasApply){
        IPage<PasApply> page = pasApplyService.getPage(current, pageSize, pasApply);
        return R.success(page);
    }

    /*
     * 管理员：旅客申请列表页面功能
     * 批量通过审批
     * */
    @PostMapping("/applyStatus")
    public R applyStatus(@RequestBody List<Integer> ids) {
        List<PasApply> pasApplies = pasApplyService.listByIds(ids);
        for (PasApply pasApply : pasApplies) {
            pasApply.setStatus("1");
            save(pasApply);
        }
        return R.success(true);
    }

    /*
     * 管理员：旅客申请列表页面功能
     * 批量通过审批
     * */
    @PostMapping("/refuseStatus")
    public R refuseStatus(@RequestBody List<Integer> ids) {
        List<PasApply> pasApplies = pasApplyService.listByIds(ids);
        for (PasApply pasApply : pasApplies) {
            pasApply.setStatus("0");
            save(pasApply);
        }
        return R.success(true);
    }

}

