package com.example.cwsd.Equipment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.third.zhgd.Equipment.entity.Equipment;

import java.util.List;

/**
* <p>
    * 用户表 服务类接口
    * </p>
*
* @author Csk
* @since 2024-05-21
*/
public interface EquipmentService extends IService<Equipment> {


    Boolean addOrUpdate(Equipment equipment);
    void deleteByIds(String ids);
    List<Equipment> list(String name,String address);
    Page<Equipment> page(Integer pageNum,Integer pageSize);

    int updateValueByRandom();
}