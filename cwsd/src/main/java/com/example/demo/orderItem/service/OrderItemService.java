package com.example.demo.orderItem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.orderItem.entity.OrderItem;

import java.util.List;

/**
* <p>
    * 订单表 服务类接口
    * </p>
*
* @author Csk
* @since 2023-12-21
*/
public interface OrderItemService extends IService<OrderItem> {


    Boolean addOrUpdate(OrderItem orderItem);
    void deleteByIds(String ids);
    List<OrderItem> list();
    Page<OrderItem> page(Integer pageNum,Integer pageSize);

}