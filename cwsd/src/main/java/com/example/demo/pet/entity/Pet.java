package com.example.demo.pet.entity;

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
@TableName("tb_pet")
@ApiModel(value="Pet对象", description="商品表")
public class Pet extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品名字")
    private String name;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "关联tb_商品类别表")
    private String categoryId;

    @ApiModelProperty(value = "价格")
    private Double price;

    @ApiModelProperty(value = "宠物种类")
    private Integer species;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private Integer gender;

}
