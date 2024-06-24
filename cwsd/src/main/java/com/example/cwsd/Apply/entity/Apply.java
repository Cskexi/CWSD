package com.example.cwsd.Apply.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.third.zhgd.other.utils.BaseEntity;
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
 * @since 2024-05-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_apply")
@ApiModel(value="Apply对象", description="")
public class Apply extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "请假类型 0-病假 1-事假 2-年假 3-其他")
    private Integer type;

    @ApiModelProperty(value = "请假开始时间")
    private String start;

    @ApiModelProperty(value = "请假结束时间")
    private String end;

    @ApiModelProperty(value = "请假原因")
    private String reason;

    @ApiModelProperty(value = "状态 0-待审核 1-审核通过 2-审核不通过")
    private Integer state;

    @ApiModelProperty(value = "用户表ID")
    private String userId;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "审核原因")
    private String vetReason;

}
