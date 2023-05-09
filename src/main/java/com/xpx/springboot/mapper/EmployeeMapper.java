package com.xpx.springboot.mapper;

import com.xpx.springboot.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-27
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    @Select("SELECT COUNT(*) FROM `employee`")
    int countemp();
}
