package com.example.demo.aop.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.example.demo.springboot2023.utils.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author Chrimer
 * @since 2023-12-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_log")
@ApiModel(value="SysLog对象", description="操作日志表")
public class SysLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "访问的ip")
    private String ip;

    @ApiModelProperty(value = "访问的路径-class")
    private String classPath;

    @ApiModelProperty(value = "访问的路径-method")
    private String methodPath;

    @ApiModelProperty(value = "创建时间 YYYY-MM-DD hh:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "状态 1-可用 0-不可用")
    private String userId;

    @ApiModelProperty(value = "访问的内容")
    private String content;

    @ApiModelProperty(value = "请求方式")
    private String method;

    @ApiModelProperty(value = "请求参数")
    private String param;


}
