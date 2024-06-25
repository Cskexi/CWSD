package com.example.demo.orderItems.entity;

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
@TableName("tb_order_items")
@ApiModel(value="OrderItems对象", description="订单表")
public class OrderItems extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号id，关联order表")
    private String ordersId;

    @ApiModelProperty(value = "物品id，关联goods表")
    private String productId;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "物品数量")
    private Integer number;

    @ApiModelProperty(value = "订单时的价格")
    private Integer priceAtOrder;


}
