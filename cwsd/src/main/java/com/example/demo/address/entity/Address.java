package com.example.demo.address.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.springboot2023.utils.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 地址表
 * </p>
 *
 * @author Csk
 * @since 2024-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_address")
@ApiModel(value="Address对象", description="地址表")
public class Address extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "详细地址")
    private String detail;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "收件人姓名")
    @TableField("userName")
    private String userName;

    @ApiModelProperty(value = "收件人电话")
    @TableField("telNumber")
    private String telNumber;

    @ApiModelProperty(value = "邮政编码")
    @TableField("postalCode")
    private String postalCode;


}
