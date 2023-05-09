package com.xpx.springboot.controller.dto;


import com.xpx.springboot.entity.SysMenu;
import lombok.Data;

import java.util.List;

/*
* 接受前端登录数据
* */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String name;
    private String token;

    private String role;
    private List<SysMenu> menus;
}
