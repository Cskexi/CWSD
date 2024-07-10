package com.example.demo.products.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Store.entity.Store;
import com.example.demo.Store.service.StoreService;
import com.example.demo.categories.entity.Categories;
import com.example.demo.chapter3.entity.User;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.products.mapper.ProductsMapper;
import com.example.demo.products.entity.Products;
import com.example.demo.products.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 商品表 服务层实现类
    * </p>
*
* @author Csk
* @since 2024-06-25
*/
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper,Products> implements ProductsService {

    @Autowired
    private StoreService storeService;

    @Override
    public Boolean addOrUpdate(Products products) {
        if(StringUtils.isBlank(products.getId())){
            //add
            products.setCreateTime(DateTool.getCurrTime());
            this.save(products);
        }else{
            //update
            this.updateById(products);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
        Products products = this.getById(id);
        products.setDelFlag(ConstantsUtils.GL_DEL);
        this.updateById(products);
        }

    }


    @Override
    public List<Products> list() {
        return this.list();
    }

    @Override
    public List<Products> listSearch(Products products) {
        QueryWrapper<Products> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Products::getDelFlag,ConstantsUtils.GL_NORMAL);
        if(StringUtils.isNotBlank(products.getName())) {
            queryWrapper.lambda().like(Products::getName,products.getName());
        }
        queryWrapper.lambda().orderByDesc(Products::getCreateTime);
        List<Products> list =this.list(queryWrapper);

            for(Products products1 : list){
                Store store = storeService.getById(products1.getStoreId());
                products1.put("store",store);
            }


        return list;
    }


    @Override
    public Page<Products> page(Integer pageNum,Integer pageSize,String name) {
        Page<Products> page = new Page(pageNum,pageSize);
        LambdaQueryWrapper<Products> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Products::getDelFlag, ConstantsUtils.GL_NORMAL);
        if(StringUtils.isNotBlank(name)){
            lambdaQueryWrapper.like(Products::getName,name);
        }
        lambdaQueryWrapper.orderByDesc(Products::getCreateTime);
        page = this.page(page, lambdaQueryWrapper);
        return page;
    }

}