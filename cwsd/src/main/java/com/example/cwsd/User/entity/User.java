package com.example.cwsd.User.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.cwsd.other.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Csk
 * @since 2024-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_user")
@ApiModel(value="User对象", description="用户表")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "状态 1-可用 0-不可用")
    private Integer state;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "盐")
    private String salt;

    @ApiModelProperty(value = "手机号码")
    private String telephone;

    @ApiModelProperty(value = "性别 1-男 0-女")
    private String sex;

    @ApiModelProperty(value = "角色 0-管理员 1-施工人员 2-施工经理 ")
    private String type;

    @ApiModelProperty(value = "姓名")
    private String name;



}
