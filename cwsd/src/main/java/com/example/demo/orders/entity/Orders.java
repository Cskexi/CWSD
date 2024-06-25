package com.example.demo.orders.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.springboot2023.utils.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author Csk
 * @since 2024-06-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_orders")
@ApiModel(value="Orders对象", description="订单表")
public class Orders extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单编号")
    private String no;

    @ApiModelProperty(value = "订单创建者，关联user表")
    private String userId;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "订单总价")
    private Double totalPrice;

    @ApiModelProperty(value = "订单状态")
    private Integer status;

    @ApiModelProperty(value = "退单理由")
    private String refund;

    @ApiModelProperty(value = "管理员的退单审核结果")
    private String refundAdmin;

    @ApiModelProperty(value = "订单取消原因	")
    private String cancleReason;


}
