package com.example.demo.reviews.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.reviews.entity.Reviews;

import java.util.List;

/**
* <p>
    * 订单表 服务类接口
    * </p>
*
* @author Csk
* @since 2024-06-25
*/
public interface ReviewsService extends IService<Reviews> {


    Boolean addOrUpdate(Reviews reviews);
    void deleteByIds(String ids);
    List<Reviews> list();
    Page<Reviews> page(Integer pageNum,Integer pageSize);

}