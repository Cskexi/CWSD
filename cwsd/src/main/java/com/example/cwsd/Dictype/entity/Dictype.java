package com.example.cwsd.Dictype.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.third.zhgd.other.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author Csk
 * @since 2024-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_dictype")
@ApiModel(value="Dictype对象", description="字典类型表")
public class Dictype extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典类型名称")
    private String name;

    @ApiModelProperty(value = "字典类型值")
    private String value;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;


}
