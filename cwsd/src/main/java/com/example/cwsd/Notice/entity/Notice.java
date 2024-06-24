package com.example.cwsd.Notice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.cwsd.other.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Csk
 * @since 2024-05-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_notice")
@ApiModel(value="Notice对象", description="")
public class Notice extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "发生时间")
    private String time;

    @ApiModelProperty(value = "通知说明")
    private String reason;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "状态 1-已处理 0-未处理")
    private Integer state;

    @ApiModelProperty(value = "设备id")
    private String equipmentId;

}
