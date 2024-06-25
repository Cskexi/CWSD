package com.example.demo.Goods.vo;

import com.example.demo.springboot2023.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Dictype书籍实体信息",description = "字典类型实体信息")
public class GoodsStoreVO extends BaseEntity {
    @ApiModelProperty(value = "商品名字")
    private String name;

    @ApiModelProperty(value = "商品图片")
    private String pic;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "所属商店id,关联tb_store表")
    private String storeId;

    @ApiModelProperty(value = "价格")
    private Double price;

    @ApiModelProperty(value = "库存")
    private Integer inventory;


}
