package com.example.demo.Store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Store.entity.Store;
import com.example.demo.Store.vo.StoreUserVO;
import com.example.demo.chapter4.vo.DictypeUserVO;

import java.util.List;

/**
* <p>
    * 字典类型表 服务类接口
    * </p>
*
* @author Csk
* @since 2023-12-18
*/
public interface StoreService extends IService<Store> {


    Page<StoreUserVO> page2(Integer pageNum, Integer pageSize, String name);

    Boolean addOrUpdate(Store store);
    void deleteByIds(String ids);
    List<Store> list();
    Page<Store> page(Integer pageNum,Integer pageSize,String userId);
    Store myGetById(String id);

    Store GetByUserId(String userId);

}