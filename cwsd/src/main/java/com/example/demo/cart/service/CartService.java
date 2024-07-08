package com.example.demo.cart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.cart.entity.Cart;

import java.util.List;

/**
* <p>
    * 购物车表 服务类接口
    * </p>
*
* @author Csk
* @since 2024-07-03
*/
public interface CartService extends IService<Cart> {


    Boolean addOrUpdate(Cart cart);
    void deleteByIds(String ids);
    List<Cart> list();
    Page<Cart> page(Integer pageNum,Integer pageSize);

}