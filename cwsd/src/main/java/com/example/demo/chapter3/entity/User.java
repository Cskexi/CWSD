package com.example.demo.chapter3.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.springboot2023.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("tb_user")
@ApiModel(value="User用户实体信息",description = "用户实体信息")
public class User extends BaseEntity {

    @ApiModelProperty(value = "用户名")
    private String username;

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

    @ApiModelProperty(value = "余额")
    private Double ballance;

    @ApiModelProperty(value = "性别 0-女 1-男 2-保密")
    private Integer sex;

    @ApiModelProperty(value = "联系电话")
    private String telephone;

    @ApiModelProperty(value = "用户类型 1-超级管理员 2-信息管理员 3-前端用户")
    private Integer type;

    @ApiModelProperty(value = "头像")
    private String pic;

    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "邮件地址")
    private String email;

}
