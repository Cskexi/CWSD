package com.example.demo.products.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.products.entity.Products;

import java.util.List;

/**
* <p>
    * 商品表 服务类接口
    * </p>
*
* @author Csk
* @since 2024-06-25
*/
public interface ProductsService extends IService<Products> {


    Boolean addOrUpdate(Products products);
    void deleteByIds(String ids);
    List<Products> list();
    List<Products> listSearch(String name);
    Page<Products> page(Integer pageNum,Integer pageSize,String name);

}