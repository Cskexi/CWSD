package com.example.demo.serve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.serve.entity.Serve;

import java.util.List;

/**
* <p>
    * 服务表 服务类接口
    * </p>
*
* @author Csk
* @since 2024-06-25
*/
public interface ServeService extends IService<Serve> {


    Boolean addOrUpdate(Serve serve);
    void deleteByIds(String ids);
    List<Serve> list();
    Page<Serve> page(Integer pageNum,Integer pageSize);

}