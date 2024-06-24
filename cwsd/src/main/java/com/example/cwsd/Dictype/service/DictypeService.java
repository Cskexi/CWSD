package com.example.cwsd.Dictype.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.cwsd.Dictype.entity.Dictype;

import java.util.List;

/**
* <p>
    * 字典类型表 服务类接口
    * </p>
*
* @author Csk
* @since 2024-05-14
*/
public interface DictypeService extends IService<Dictype> {


    Boolean addOrUpdate(Dictype dictype);
    void deleteByIds(String ids);
    List<Dictype> list(String name);
    Page<Dictype> page(Integer pageNum,Integer pageSize);

    Page<Dictype> pageByUsername(Integer pageNum, Integer pageSize, String name);


}