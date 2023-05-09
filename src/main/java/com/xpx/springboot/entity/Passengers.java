package com.xpx.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author 徐培鑫
 * @since 2023-03-31
 */
@Data
@Getter
@Setter
  @ApiModel(value = "Passengers对象", description = "")
public class Passengers implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("ID")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("账号")
      private String username;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("姓名")
      private String name;

      @ApiModelProperty("性别")
      private String sex;

      @ApiModelProperty("身份证号码")
      private String idNumber;

      @ApiModelProperty("电话")
      private String phone;

      @ApiModelProperty("创建时间")
      private LocalDateTime createTime;

      @ApiModelProperty("更新时间")
      private LocalDateTime lastUpdateTime;

//      //一对一
//      @TableField(exist = false)
//      private PasQs pasQs;
//
//      //一对多
//      @TableField(exist = false)
//      private List<PasQs> pasQsList;

      /*
      *   pas_apply表的变量
      * */
      @ApiModelProperty("家庭住址")
      @TableField(exist = false)
      private String homeAddress;

      @ApiModelProperty("申请前往地址")
      @TableField(exist = false)
      private String applyAddress;

      /*
      *   pas_qs表的变量
      * */
      @ApiModelProperty("隔离点编号")
      @TableField(exist = false)
      private Integer pasId;

      @ApiModelProperty("隔离点编号")
      @TableField(exist = false)
      private String qsNum;

      @ApiModelProperty("隔离名称")
      @TableField(exist = false)
      private String qsName;

      @ApiModelProperty("隔离房间")
      @TableField(exist = false)
      private String qsRoomNum;

      @ApiModelProperty("隔离开始")
      @TableField(exist = false)
//      @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//      @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
      private LocalDateTime startTime;

      @ApiModelProperty("隔离结束时间")
      @TableField(exist = false)
      private LocalDateTime endTime;


      /*
      * pas_health的变量
      * */
      @ApiModelProperty("身体情况")
      @TableField(exist = false)
      private String health;

      @ApiModelProperty("体温")
      @TableField(exist = false)
      private String temperature;

      @ApiModelProperty("是否感染")
      @TableField(exist = false)
      private String status;
}
