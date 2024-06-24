package com.example.cwsd.Monitor.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.third.zhgd.Monitor.entity.Monitor;

import java.util.List;

/**
* <p>
    *  服务类接口
    * </p>
*
* @author Csk
* @since 2024-05-14
*/
public interface MonitorService extends IService<Monitor> {


    Boolean addOrUpdate(Monitor monitor);
    void deleteByIds(String ids);
    List<Monitor> list(String name,String address);
    Page<Monitor> page(Integer pageNum,Integer pageSize);

}