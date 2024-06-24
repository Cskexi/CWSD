package com.example.cwsd.Monitor.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.third.zhgd.other.utils.BaseEntity;
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
 * @since 2024-05-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_monitor")
@ApiModel(value="Monitor对象", description="")
public class Monitor extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "视频地址")
    private String streamUrl;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "区域")
    private String address;

    @ApiModelProperty(value = "状态 1-可用 0-不可用")
    private Integer state;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

}
