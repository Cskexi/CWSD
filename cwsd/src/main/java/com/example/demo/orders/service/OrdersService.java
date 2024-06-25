package com.example.demo.orders.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.orders.entity.Orders;

import java.util.List;

/**
* <p>
    * 订单表 服务类接口
    * </p>
*
* @author Csk
* @since 2024-06-25
*/
public interface OrdersService extends IService<Orders> {


    Boolean addOrUpdate(Orders orders);
    void deleteByIds(String ids);
    List<Orders> list();
    Page<Orders> page(Integer pageNum,Integer pageSize);

}