package com.example.cwsd.Dic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.cwsd.Dic.entity.Dic;

import java.util.List;

/**
* <p>
    * 字典表  服务类接口
    * </p>
*
* @author Csk
* @since 2024-05-14
*/
public interface DicService extends IService<Dic> {


    Boolean addOrUpdate(Dic dic);
    void deleteByIds(String ids);
    List<Dic> list();
    Page<Dic> page(Integer pageNum, Integer pageSize, String name,String dictypeId);

}