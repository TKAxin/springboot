package com.xpx.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2023-04-01
 */
@Data
@Getter
@Setter
  @TableName("pas_health")
@ApiModel(value = "PasHealth对象", description = "")
public class PasHealth implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("旅客表id")
      private Integer pasId;

      @ApiModelProperty("身体情况")
      private String health;

      @ApiModelProperty("体温")
      private String temperature;

      @ApiModelProperty("是否感染")
      private String status;

      @TableField(exist = false)
      private String pusername;


}
