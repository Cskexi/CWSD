package com.example.cwsd.DataMsg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.third.zhgd.DataMsg.entity.Datamsg;

import java.util.List;

/**
* <p>
    *  服务类接口
    * </p>
*
* @author Csk
* @since 2024-05-01
*/
public interface DatamsgService extends IService<Datamsg> {


    Boolean addOrUpdate(Datamsg datamsg);
    void deleteByIds(String ids);
    List<Datamsg> list();
    Page<Datamsg> page(Integer pageNum,Integer pageSize);

    Datamsg getNum(String Name);

}