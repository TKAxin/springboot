package com.xpx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

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
  @TableName("pas_qs")
@ApiModel(value = "PasQs对象", description = "")
public class PasQs implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("隔离人员编号")
      private Integer pasId;

      @ApiModelProperty("隔离点编号")
      private String qsNum;

      @ApiModelProperty("隔离点名称")
      private String qsName;

      @ApiModelProperty("隔离房间")
      private String qsRoomNum;

      @ApiModelProperty("隔离开始时间")
//      @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//      @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
      private LocalDateTime startTime;

      @ApiModelProperty("隔离结束时间")
      private LocalDateTime endTime;


}
