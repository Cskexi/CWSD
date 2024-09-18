package com.example.demo.reviews.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.reviews.entity.Reviews;

import java.util.List;

/**
* <p>
    * 评论表 服务类接口
    * </p>
*
* @author Csk
* @since 2024-08-22
*/
public interface ReviewsService extends IService<Reviews> {


    Reviews addOrUpdate(Reviews reviews);
    void deleteByIds(String ids);
    List<Reviews> list(String productId);
    Page<Reviews> page(Integer pageNum,Integer pageSize);

}