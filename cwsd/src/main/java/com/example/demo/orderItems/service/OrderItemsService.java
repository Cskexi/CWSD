package com.example.demo.orderItems.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.orderItems.entity.OrderItems;

import java.util.List;

/**
* <p>
    * 子订单表 服务类接口
    * </p>
*
* @author Csk
* @since 2024-07-09
*/
public interface OrderItemsService extends IService<OrderItems> {


    Boolean addOrUpdate(OrderItems orderItems);
    void deleteByIds(String ids);
    List<OrderItems> list();
    Page<OrderItems> page(Integer pageNum,Integer pageSize);

}