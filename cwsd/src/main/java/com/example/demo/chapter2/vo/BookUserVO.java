package com.example.demo.chapter2.vo;

import com.example.demo.springboot2023.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Book书籍实体信息",description = "字典类型实体信息")
public class BookUserVO extends BaseEntity {
    @ApiModelProperty(value = "书籍名称")
    private String name;
    @ApiModelProperty(value = "出版社")
    private String publish;
    @ApiModelProperty(value = "价格")
    private Double price;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "状态 0-不可用 1-可用")
    private Integer state;
    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;
    @ApiModelProperty(value = "用户名")
    private String username;

}
