package com.example.demo.userAction.entity;

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
 * 用户操作记录表
 * </p>
 *
 * @author Csk
 * @since 2024-08-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_user_action")
@ApiModel(value="UserAction对象", description="用户操作记录表")
public class UserAction extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id，关联user表")
    private String userId;

    @ApiModelProperty(value = "商品id，关联product表")
    private String productId;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "操作类型 0-浏览 1-收藏")
    private Integer actionType;
}
