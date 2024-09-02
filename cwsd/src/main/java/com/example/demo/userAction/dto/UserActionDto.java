package com.example.demo.userAction.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.springboot2023.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户操作记录表
 * </p>
 *
 * @author Csk
 * @since 2024-08-22
 */
@Data
@ApiModel(value="UserAction对象", description="用户操作记录表")
public class UserActionDto extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户id，关联user表")
    private String userId;
    @ApiModelProperty(value = "商品id，关联product表")
    private String productId;
    @ApiModelProperty(value = "操作类型 0-浏览 1-收藏")
    private Integer actionType;
    private Integer pageNum;
    private Integer pageSize;
}
