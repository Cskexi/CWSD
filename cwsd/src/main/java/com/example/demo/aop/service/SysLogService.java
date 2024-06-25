package com.example.demo.aop.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.aop.entity.SysLog;

import java.util.List;

/**
* <p>
    * 操作日志表 服务类接口
    * </p>
*
* @author Chrimer
* @since 2023-12-08
*/
public interface SysLogService extends IService<SysLog> {


    Boolean addOrUpdate(SysLog sysLog);
    void deleteByIds(String ids);
    List<SysLog> list();
    Page<SysLog> page(Integer pageNum, Integer pageSize);

    SysLog myGetById(String id);
}