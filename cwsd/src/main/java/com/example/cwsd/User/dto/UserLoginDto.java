package com.example.cwsd.User.dto;


import com.third.zhgd.other.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "User用户新增编辑实体信息",description = "用户新增编辑实体信息")
public class UserLoginDto extends BaseEntity {
    @ApiModelProperty(value = "用户名")
    private String account;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "姓名")
    private String name;
}
