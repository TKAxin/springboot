package com.xpx.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
  @TableName("quarantine_sites")
@ApiModel(value = "QuarantineSites对象", description = "")
public class QuarantineSites implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String number;

    private String name;

    private String address;

    private String phone;

    private LocalDateTime createTime;

    private LocalDateTime lastUpdateTime;


}
