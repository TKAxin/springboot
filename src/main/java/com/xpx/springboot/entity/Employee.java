package com.xpx.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-27
 */
@Data
@Getter
@Setter
  @ApiModel(value = "Employee对象", description = "")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("账户")
      private String username;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("姓名")
      private String name;

      @ApiModelProperty("邮箱")
      private String email;

      @ApiModelProperty("电话")
      private String phone;

      @ApiModelProperty("性别")
      private String sex;

      @ApiModelProperty("身份证号码")
      private String idNumber;

      @ApiModelProperty("状态")
      private String status;

      @ApiModelProperty("创建时间")
      private LocalDateTime createTime;

      @ApiModelProperty("最后一次修改时间")
      private LocalDateTime lastUpdateTime;

      @ApiModelProperty("角色")
      private String role;

}
