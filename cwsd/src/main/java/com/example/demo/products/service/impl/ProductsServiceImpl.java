package com.example.demo.products.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Store.entity.Store;
import com.example.demo.Store.service.StoreService;
import com.example.demo.categories.entity.Categories;
import com.example.demo.chapter3.entity.User;
import com.example.demo.products.dto.ProductsDto;
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
import org.springframework.web.bind.annotation.RequestBody;

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
            products.setPic("/img/blank.png");
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
        if(StringUtils.isNotBlank(products.getStoreId())){
            queryWrapper.lambda().like(Products::getStoreId,products.getStoreId());
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

    @Override
    public Page<Products> page2(ProductsDto productsDto) {
        Page<Products> page = new Page(productsDto.getPageNum(),productsDto.getPageSize());
        LambdaQueryWrapper<Products> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Products::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.eq(Products::getStatus, 1);
        if (StringUtils.isNotBlank(productsDto.getName())) {

            lambdaQueryWrapper.like(Products::getName, productsDto.getName());
        }
        if (StringUtils.isNotBlank(productsDto.getCategoryId())) {
            lambdaQueryWrapper.like(Products::getCategoryId, productsDto.getCategoryId());
        }
        if (StringUtils.isNotBlank(productsDto.getStoreId())) {
            lambdaQueryWrapper.like(Products::getStoreId, productsDto.getStoreId());
        }
        // 定义α和β的值用于评分计算
        double alpha = 0.7; // 销售量权重
        double beta = 0.3;  // 浏览次数权重

        // 添加排序逻辑，使用SQL片段进行评分排序
        lambdaQueryWrapper.last("ORDER BY (sold_quantity * " + alpha + " + view * " + beta + ") DESC");
        //lambdaQueryWrapper.orderByDesc(Products::getSoldQuantity * alpha + " + view_count * " + beta + ")");

        //lambdaQueryWrapper.orderByDesc(Products::getCreateTime);
        //lambdaQueryWrapper.orderByDesc(Products::getSoldQuantity);
        page = this.page(page, lambdaQueryWrapper);
        return page;
    }

    @Override
    public Products getById2(String id) {
        Products products= this.getById(id);
        Store store = storeService.getById(products.getStoreId());
        products.put("store",store);
        return products;
    }

    @Override
    public void viewChange() {
        baseMapper.createTempTable();
        baseMapper.insertIntoTempTable();
        baseMapper.updateGoodsView();
        baseMapper.dropTempTable();
        System.out.println("Browse counts updated successfully.");
    }

}