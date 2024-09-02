package com.example.demo.reviews.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.springboot2023.utils.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author Csk
 * @since 2024-08-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_reviews")
@ApiModel(value="Reviews对象", description="评论表")
public class Reviews extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id，关联user表")
    private String userId;

    @ApiModelProperty(value = "物品id，关联goods表")
    private String productId;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "评论")
    private String reviews;

    @ApiModelProperty(value = "评分1-5")
    private Integer rating;

    @ApiModelProperty(value = "订单id,关联order_items表")
    private String orderId;


}
