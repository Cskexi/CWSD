package com.example.demo.serve.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.springboot2023.utils.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 服务表
 * </p>
 *
 * @author Csk
 * @since 2024-06-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_serve")
@ApiModel(value="Serve对象", description="服务表")
public class Serve extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "服务名字")
    private String name;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "价格")
    private Double price;

    @ApiModelProperty(value = "持续时间")
    private Integer duration;

    @ApiModelProperty(value = "商品种类表")
    private String categoryId;


}
