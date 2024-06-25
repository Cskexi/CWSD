package com.example.demo.chapter4.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.springboot2023.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("tb_dic")
@ApiModel(value="Dic实体信息",description = "字典实体信息")
public class Dic extends BaseEntity {
    @ApiModelProperty(value = "字典类型名称")
    private String name;
    @ApiModelProperty(value = "字典类型值")
    private String value;
    @ApiModelProperty(value = "所属字典类型，关联tb_dictype表")
    private String dictypeId;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;
}
