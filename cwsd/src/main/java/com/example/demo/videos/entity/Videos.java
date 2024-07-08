package com.example.demo.videos.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.springboot2023.utils.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 视频表
 * </p>
 *
 * @author Csk
 * @since 2024-07-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("tb_videos")
@ApiModel(value="Videos对象", description="视频表")
public class Videos extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物品id，关联goods表")
    private String productId;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "是否删除 1-正常 0-已删除")
    private Integer delFlag;

    @ApiModelProperty(value = "路径")
    private String videoUrl;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "播放次数")
    private Integer viewsCount;


}
