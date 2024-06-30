package com.example.demo.categories.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.springboot2023.utils.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典表 
 * </p>
 *
 * @author Csk
 * @since 2024-06-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_categories")
@ApiModel(value="Categories对象", description="字典表 ")
public class Categories extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类别名称")
    private String name;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "上一级类别")
    private String parentCategoryId;

    @ApiModelProperty(value = "最高级级类别")
    private String firstCategoryId;



}
