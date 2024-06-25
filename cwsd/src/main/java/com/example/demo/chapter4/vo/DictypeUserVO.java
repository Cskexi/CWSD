package com.example.demo.chapter4.vo;

import com.example.demo.springboot2023.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Dictype书籍实体信息",description = "字典类型实体信息")
public class DictypeUserVO extends BaseEntity {
    @ApiModelProperty(value = "字典类型名称")
    private String name;
    @ApiModelProperty(value = "所属用户，关联tb_user表")
    private String userId;
    @ApiModelProperty(value = "字典类型值")
    private String value;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "用户名")
    private String username;

}
