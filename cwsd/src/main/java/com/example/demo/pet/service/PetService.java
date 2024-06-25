package com.example.demo.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pet.entity.Pet;

import java.util.List;

/**
* <p>
    * 商品表 服务类接口
    * </p>
*
* @author Csk
* @since 2024-06-25
*/
public interface PetService extends IService<Pet> {


    Boolean addOrUpdate(Pet pet);
    void deleteByIds(String ids);
    List<Pet> list();
    Page<Pet> page(Integer pageNum,Integer pageSize);

}