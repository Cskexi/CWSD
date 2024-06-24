package com.example.cwsd.DataMsg.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.cwsd.other.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Csk
 * @since 2024-05-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_datamsg")
@ApiModel(value="Datamsg对象", description="")
public class Datamsg extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据名")
    @TableField("dataName")
    private String dataName;

    @ApiModelProperty(value = "数据量")
    @TableField("dataNum")
    private Integer dataNum;


}
