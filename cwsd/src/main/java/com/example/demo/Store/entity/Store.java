package com.example.demo.Store.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.springboot2023.utils.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author Csk
 * @since 2023-12-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_store")
@ApiModel(value="Store对象", description="字典类型表")
public class Store extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商店名称")
    private String name;

    @ApiModelProperty(value = "商店地址")
    private String address;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "商店所属管理员,所属用户关联tb_user表")
    private String userId;

    @ApiModelProperty(value = "联系电话")
    private String telephone;

    @ApiModelProperty(value = "联系电话")
    private String info;

}
