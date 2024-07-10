package com.example.demo.orderItems.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.orderItems.mapper.OrderItemsMapper;
import com.example.demo.orderItems.entity.OrderItems;
import com.example.demo.orderItems.service.OrderItemsService;
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

    @Override
    public Boolean addOrUpdate(OrderItems orderItems) {
        Random rand = new Random();
        if(StringUtils.isBlank(orderItems.getId())){
            //add
            String str ="";
            for (int i = 0; i < 4; i++) {
                str = str + (char)(Math.random()*26+'a');
            }
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
            String str2=sdf.format(new Date());
            orderItems.setNo("no"+str2+(rand.nextInt(9000) + 1000)+str);
            orderItems.setCreateTime(DateTool.getCurrTime());
            this.save(orderItems);
        }else{
            //update
            this.updateById(orderItems);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
        OrderItems orderItems = this.getById(id);
        orderItems.setDelFlag(ConstantsUtils.GL_DEL);
        this.updateById(orderItems);
        }

    }


    @Override
    public List<OrderItems> list() {
        QueryWrapper<OrderItems> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(OrderItems::getDelFlag,ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(OrderItems::getCreateTime);
        List<OrderItems> list =this.list(queryWrapper);
        return this.list();
    }


    @Override
    public Page<OrderItems> page(Integer pageNum,Integer pageSize) {
        Page<OrderItems> page = new Page(pageNum,pageSize);
        LambdaQueryWrapper<OrderItems> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OrderItems::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(OrderItems::getCreateTime);
        page = this.page(page, lambdaQueryWrapper);
        return page;
    }

}