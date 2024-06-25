package com.example.demo.categories.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.categories.entity.Categories;

import java.util.List;

/**
* <p>
    * 字典表  服务类接口
    * </p>
*
* @author Csk
* @since 2024-06-25
*/
public interface CategoriesService extends IService<Categories> {


    Boolean addOrUpdate(Categories categories);
    void deleteByIds(String ids);
    List<Categories> list();
    Page<Categories> page(Integer pageNum,Integer pageSize);

}