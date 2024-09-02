package com.example.demo.products.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.springboot2023.utils.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author Csk
 * @since 2024-06-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_products")
@ApiModel(value="Products对象", description="商品表")
public class Products extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品名字")
    private String name;

    @ApiModelProperty(value = "商品图片")
    private String pic;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "所属商店id,关联tb_store表")
    private String storeId;

    @ApiModelProperty(value = "价格")
    private Double price;

    @ApiModelProperty(value = "库存")
    private Integer inventory;

    @ApiModelProperty(value = "状态 1-正常 0-下架")
    private Integer status;

    @ApiModelProperty(value = "商品类别表ID")
    private String categoryId;

    @ApiModelProperty(value = "折扣")
    private String  discount;

    @ApiModelProperty(value = "描述")
    private String  description;

    @ApiModelProperty(value = "售出数量")
    private Integer soldQuantity;

    @ApiModelProperty(value = "浏览次数")
    private Integer view;






}
