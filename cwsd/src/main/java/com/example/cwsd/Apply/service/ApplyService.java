package com.example.cwsd.Apply.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.cwsd.Apply.entity.Apply;

import java.util.List;

/**
* <p>
    *  服务类接口
    * </p>
*
* @author Csk
* @since 2024-05-03
*/
public interface ApplyService extends IService<Apply> {


    Boolean addOrUpdate(Apply apply);
    void deleteByIds(String ids);
    List<Apply> list();
    Page<Apply> page(Integer pageNum,Integer pageSize);
    Page<Apply> myPage(Integer pageNum,Integer pageSize,String userId);
}