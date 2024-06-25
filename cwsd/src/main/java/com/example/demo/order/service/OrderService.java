package com.example.demo.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.order.entity.Order;

import java.util.List;

/**
* <p>
    * 订单表 服务类接口
    * </p>
*
* @author Csk
* @since 2023-12-21
*/
public interface OrderService extends IService<Order> {


    String addOrUpdate(Order order);
    void deleteByIds(String ids);
    List<Order> list(String id);
    List<Order> list2(String id);
    Page<Order> page(Integer pageNum,Integer pageSize);

}