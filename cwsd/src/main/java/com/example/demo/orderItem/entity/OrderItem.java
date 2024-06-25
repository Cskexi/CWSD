package com.example.demo.orderItem.entity;

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
 * @since 2023-12-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_order_item")
@ApiModel(value="OrderItem对象", description="订单表")
public class OrderItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号id，关联order表")
    private String orderId;

    @ApiModelProperty(value = "物品id，关联goods表")
    private String goodsId;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "物品数量")
    private Integer number;


}
