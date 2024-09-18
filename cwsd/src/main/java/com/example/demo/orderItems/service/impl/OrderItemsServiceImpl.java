package com.example.demo.orderItems.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Goods.entity.Goods;
import com.example.demo.Store.entity.Store;
import com.example.demo.Store.service.StoreService;
import com.example.demo.categories.entity.Categories;
import com.example.demo.chapter3.entity.User;
import com.example.demo.order.entity.Order;
import com.example.demo.products.entity.Products;
import com.example.demo.products.service.ProductsService;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.orderItems.mapper.OrderItemsMapper;
import com.example.demo.orderItems.entity.OrderItems;
import com.example.demo.orderItems.service.OrderItemsService;
import com.example.demo.springboot2023.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 子订单表 服务层实现类
    * </p>
*
* @author Csk
* @since 2024-07-09
*/
@Service
public class OrderItemsServiceImpl extends ServiceImpl<OrderItemsMapper,OrderItems> implements OrderItemsService {
    @Autowired
    private ProductsService productsService;

    @Override
    public OrderItems addOrUpdate(OrderItems orderItems) {
        Random rand = new Random();
//        if(StringUtils.isBlank(orderItems.getId())){
//            //add
//            String str ="";
//            for (int i = 0; i < 4; i++) {
//                str = str + (char)(Math.random()*26+'a');
//            }
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
//            String str2=sdf.format(new Date());
//            orderItems.setNo("no"+str2+(rand.nextInt(9000) + 1000)+str);
//            orderItems.setCreateTime(DateTool.getCurrTime());
//
//            Products products = productsService.getById(orderItems.getProductId());
//
//            products.setInventory(products.getInventory()-orderItems.getNumber());
//            if(products.getInventory()<=0){
//                products.setStatus(2);
//            }
//            productsService.updateById(products);
//
//            this.save(orderItems);
//        }else{
//            if(orderItems.getStatus()==-1){
//                OrderItems orderItems1 = getById(orderItems.getId());
//                if(orderItems1.getStatus()!=0&&orderItems1.getStatus()!=1){
//                    return orderItems1;
//                }
//            }
//            //update
//            this.updateById(orderItems);
//        }
//        return orderItems;


        try {
            // 更新产品库存


            // 提交订单项
            if (StringUtils.isBlank(orderItems.getId())) {

                String str ="";
                for (int i = 0; i < 4; i++) {
                    str = str + (char)(Math.random()*26+'a');
                }
                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
                String str2=sdf.format(new Date());
                orderItems.setNo("no"+str2+(rand.nextInt(9000) + 1000)+str);
                orderItems.setCreateTime(DateTool.getCurrTime());

                Products products = productsService.getById(orderItems.getProductId());
                products.setInventory(products.getInventory()-orderItems.getNumber());
                if(products.getInventory()<=0){
                    products.setStatus(2);
                }
                productsService.updateById(products);

                this.save(orderItems);
            } else {
                if(orderItems.getStatus()==-1){
                    OrderItems orderItems1 = getById(orderItems.getId());
                    if(orderItems1.getStatus()!=0&&orderItems1.getStatus()!=1){
                        return orderItems1;
                    }
                }
                if(orderItems.getStatus()==-1||orderItems.getStatus()<=-4){
                    Products products = productsService.getById(orderItems.getProductId());
                    products.setInventory(products.getInventory()+orderItems.getNumber());
                    if(products.getInventory()>0){
                        products.setStatus(1);
                    }
                    productsService.updateById(products);
                }

                //update
                this.updateById(orderItems);
            }
        } catch (OptimisticLockingFailureException e) {
            // 处理并发修改冲突
            log.error("Concurrent modification detected during addOrUpdate operation.", e);
            throw e;
        }

        return orderItems;

    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");

        try {
            for(String id: aryIds) {
                // 获取订单项
                OrderItems orderItems = this.getById(id);

                // 更新产品库存
            Products products = productsService.getById(orderItems.getProductId());
            products.setInventory(products.getInventory()+orderItems.getNumber());
            if(products.getInventory()>0){
                products.setStatus(1);
            }
            productsService.updateById(products);

                // 删除订单项
                orderItems.setDelFlag(ConstantsUtils.GL_DEL);
                this.removeById(orderItems);
            }
        } catch (OptimisticLockingFailureException e) {
            // 处理并发修改冲突
            log.error("Concurrent modification detected during deleteByIds operation.", e);
            throw e;
        }
    }


    @Override
    public List<OrderItems> list(String userId) {
        if(StringUtils.isNotBlank(userId)){
            QueryWrapper<OrderItems> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(OrderItems::getDelFlag,ConstantsUtils.GL_NORMAL);
            queryWrapper.lambda().like(OrderItems::getUserId, userId);
            queryWrapper.lambda().orderByDesc(OrderItems::getCreateTime);
            List<OrderItems> list =this.list(queryWrapper);
            for(OrderItems orderItems : list){
                Products products = new Products();
                products = productsService.getById(orderItems.getProductId());
                orderItems.put("products",products);
            }
            return list;
        }
            return null;
    }


    @Override
    public Page<OrderItems> page(Integer pageNum,Integer pageSize,String storeId) {
        Page<OrderItems> page = new Page(pageNum,pageSize);
        LambdaQueryWrapper<OrderItems> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OrderItems::getDelFlag, ConstantsUtils.GL_NORMAL);
        if (StringUtils.isNotBlank(storeId)){
            lambdaQueryWrapper.like(OrderItems::getStoreId, storeId);
        }
        lambdaQueryWrapper.orderByDesc(OrderItems::getCreateTime);
        page = this.page(page, lambdaQueryWrapper);
        for(OrderItems orderItems:page.getRecords()){//分页信息记录数
            Products products = productsService.getById(orderItems.getProductId());
            orderItems.put("products",products);
        }
        return page;
    }

}