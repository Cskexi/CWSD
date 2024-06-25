package com.example.demo.chapter3.dto;

import com.example.demo.springboot2023.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "User用户新增编辑实体信息",description = "用户新增编辑实体信息")
public class UserLoginDto extends BaseEntity {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
}
