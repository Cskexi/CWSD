package com.example.demo.address.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.address.entity.Address;

import java.util.List;

/**
* <p>
    * 地址表 服务类接口
    * </p>
*
* @author Csk
* @since 2024-07-09
*/
public interface AddressService extends IService<Address> {


    String addOrUpdate(Address address);
    void deleteByIds(String ids);
    List<Address> list();
    Page<Address> page(Integer pageNum,Integer pageSize);

}