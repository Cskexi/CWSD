package com.example.demo.Goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Goods.entity.Goods;

import java.util.List;

/**
* <p>
    * 商品表 服务类接口
    * </p>
*
* @author Csk
* @since 2023-12-19
*/
public interface GoodsService extends IService<Goods> {
    Boolean addOrUpdate(Goods goods);
    void deleteByIds(String ids);
    List<Goods> list();
    Page<Goods> page(Integer pageNum,Integer pageSize,String name,String storeId);
    Page<Goods> page2(Integer pageNum,Integer pageSize, String name, String storeId);

}