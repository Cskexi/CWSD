package com.example.demo.Store.vo;

import com.example.demo.springboot2023.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StoreUserVO extends BaseEntity {
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
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户类型")
    private String type;
}
