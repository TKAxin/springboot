package com.xpx.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xpx.entity.PasQs;
import com.xpx.common.R;
import com.xpx.entity.PasHealth;
import com.xpx.entity.Passengers;
import com.xpx.mapper.PasApplyMapper;
import com.xpx.mapper.PassengersMapper;
import com.xpx.service.IPasHealthService;
import com.xpx.service.IPasQsService;
import com.xpx.service.IPassengersService;
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
@RequestMapping("/pas-qs")
public class PasQsController {
    @Autowired
    private IPasQsService pasQsService;

    @Autowired
    private IPassengersService passengersService;
    private PassengersMapper passengersMapper;

    @Autowired
    private PasApplyMapper pasApplyMapper;

    @Autowired
    private IPasHealthService pasHealthService;

    /*
    * 管理员：分配隔离点页面功能
    * 修改对应旅客的隔离点分配信息
    * */
    @PostMapping
    public R save(@RequestBody PasQs pasQs) {
        pasQsService.saveOrUpdate(pasQs);

        LambdaQueryWrapper<PasHealth> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PasHealth::getPasId,pasQs.getPasId());
        PasHealth one = pasHealthService.getOne(lqw);
        PasHealth pasHealth = new PasHealth();
        pasHealth.setPasId(pasQs.getPasId());
        if (one == null){
            pasHealthService.save(pasHealth);
        }
        return R.success("添加或修改成功");
    }

    //根据ID删除
    @DeleteMapping("{id}/{pasId}")
    public R delete(@PathVariable Integer id,@PathVariable Integer pasId){
        pasApplyMapper.updateStatusByPid(pasId);
        boolean removeById = pasQsService.removeById(id);
        QueryWrapper qw = new QueryWrapper();
        qw.eq("pas_id",pasId);
        pasHealthService.remove(qw);
        return R.success(removeById);
    }

    //批量删除
    @PostMapping("/delBatch")
    public R deleteBatch(@RequestBody List<Integer> ids){
        boolean removeByIds = pasQsService.removeByIds(ids);
        return R.success(removeByIds);
    }

    @GetMapping("{current}/{pageSize}")
    public R<IPage> getPage(@PathVariable int current,
                            @PathVariable int pageSize,
                            PasQs pasQs){
        IPage<PasQs> page = pasQsService.getPage(current, pageSize, pasQs);
        return R.success(page);
    }


    /*
    * 管理员页面
    * 1.查询passengers表和pas_qs表
    * 2.输出到 分配隔离点页面
    * */
    @GetMapping("/pagePasQs/{current}/{pageSize}")
    public Page<Passengers> getPadQs(@PathVariable Integer current,
                                     @PathVariable Integer pageSize){
        Page<Passengers> iPage = new Page<>(current, pageSize);
        return passengersService.getPasQs(iPage);
    }

    /*
     * 管理员页面
     * 1.查询passengers表和pas_qs表
     * 2.输出到 分配隔离点页面
     * */
    @GetMapping("/pagePasQsByName/{current}/{pageSize}/{name}")
    public Page<Passengers> getPadQsByName(@PathVariable Integer current,
                                           @PathVariable Integer pageSize,
                                           @PathVariable String name){
        Page<Passengers> iPage = new Page<>(current, pageSize);
        return passengersService.getPasQsByName(iPage,name);
    }

}

