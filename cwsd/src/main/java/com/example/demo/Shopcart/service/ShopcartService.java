package com.example.demo.Shopcart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Shopcart.entity.Shopcart;

import java.util.List;

/**
* <p>
    * 订单表 服务类接口
    * </p>
*
* @author Csk
* @since 2023-12-21
*/
public interface ShopcartService extends IService<Shopcart> {


    Boolean addOrUpdate(Shopcart shopcart);
    void deleteByIds(String ids);
    List<Shopcart> list(String id);
    Page<Shopcart> page(Integer pageNum,Integer pageSize);

}