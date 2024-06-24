package com.example.cwsd.Equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.third.zhgd.other.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Csk
 * @since 2024-05-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_equipment")
@ApiModel(value="Equipment对象", description="用户表")
public class Equipment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "状态 1-可用 0-不可用")
    private Integer state;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "警戒线")
    private String cordon;

    @ApiModelProperty(value = "起始")
    private String start;

    @ApiModelProperty(value = "终止")
    private String end;

    @ApiModelProperty(value = "设备地址")
    private String address;

    @ApiModelProperty(value = "单位")
    private String flats;

    @ApiModelProperty(value = "视频地址")
    private String streamUrl;

    @ApiModelProperty(value = "视频地址")
    private String value;
}
