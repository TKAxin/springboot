package com.xpx.springboot.exception;

import com.xpx.springboot.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 异常处理方法
     * 1.处理用户名添加时，新增或修改的用户名已经在数据库中存在
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());

        if(ex.getMessage().contains("Duplicate entry")){
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return R.error(msg);
        }
        return R.error("未知错误");
    }

    /*
    * 解决登录查询账号为空的异常
    * */

    @ExceptionHandler(NullPointerException.class)
    public R<String> exceptionHandler2(NullPointerException ex){
        log.error(ex.getMessage());
//
//        if(ex.getMessage().contains("Duplicate entry")){
//            String[] split = ex.getMessage().split(" ");
//            String msg = split[2] + "已存在";
//            return R.error(msg);
//        }
        return R.error("未知错误");
    }


    @ExceptionHandler(ServiceException.class)
    public R<String> exceptionHandler3(ServiceException ex){
        return R.error(ex.getCode(),ex.getMessage());
    }
}
