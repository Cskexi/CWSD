package com.example.demo.address.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.address.mapper.AddressMapper;
import com.example.demo.address.entity.Address;
import com.example.demo.address.service.AddressService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 地址表 服务层实现类
    * </p>
*
* @author Csk
* @since 2024-07-09
*/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper,Address> implements AddressService {

    @Override
    public String addOrUpdate(Address address) {
        if(StringUtils.isBlank(address.getId())){
            //add
            address.setCreateTime(DateTool.getCurrTime());
            this.save(address);
        }else{
            //update
            this.updateById(address);
        }
        return address.getId();
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
        Address address = this.getById(id);
        address.setDelFlag(ConstantsUtils.GL_DEL);
        this.updateById(address);
        }

    }


    @Override
    public List<Address> list() {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Address::getDelFlag,ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(Address::getCreateTime);
        List<Address> list =this.list(queryWrapper);
        return this.list();
    }


    @Override
    public Page<Address> page(Integer pageNum,Integer pageSize) {
        Page<Address> page = new Page(pageNum,pageSize);
        LambdaQueryWrapper<Address> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Address::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(Address::getCreateTime);
        page = this.page(page, lambdaQueryWrapper);
        return page;
    }

}