package com.example.demo.navigation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.navigation.entity.Navigation;

import java.util.List;

/**
* <p>
    * 地址表 服务类接口
    * </p>
*
* @author Csk
* @since 2024-06-30
*/
public interface NavigationService extends IService<Navigation> {


    Boolean addOrUpdate(Navigation navigation);
    void deleteByIds(String ids);
    List<Navigation> list();
    Page<Navigation> page(Integer pageNum,Integer pageSize);

}