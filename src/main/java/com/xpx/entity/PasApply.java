package com.xpx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-03-31
 */
@Data
@Getter
@Setter
  @TableName("pas_apply")
@ApiModel(value = "PasApply对象", description = "")
public class PasApply implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("姓名")
      private String name;

      @ApiModelProperty("性别")
      private String sex;

      @ApiModelProperty("身份证号码")
      private String idNumber;

      @ApiModelProperty("电话")
      private String phone;

    private String homeAddress;

    private String applyAddress;

      @ApiModelProperty("审批情况，默认0")
      private String status;

      private String pusername;

      private Integer pasId;


}
