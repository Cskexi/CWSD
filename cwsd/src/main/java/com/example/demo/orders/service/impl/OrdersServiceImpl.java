package com.example.demo.orders.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.chapter3.entity.User;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.orders.mapper.OrdersMapper;
import com.example.demo.orders.entity.Orders;
import com.example.demo.orders.service.OrdersService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 订单表 服务层实现类
    * </p>
*
* @author Csk
* @since 2024-07-03
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper,Orders> implements OrdersService {

    @Override
    public Boolean addOrUpdate(Orders orders) {
        if(StringUtils.isBlank(orders.getId())){
            //add
            orders.setCreateTime(DateTool.getCurrTime());
            this.save(orders);
        }else{
            //update
            this.updateById(orders);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
        Orders orders = this.getById(id);
        orders.setDelFlag(ConstantsUtils.GL_DEL);
        this.updateById(orders);
        }

    }


    @Override
    public List<Orders> list() {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Orders::getDelFlag,ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(Orders::getCreateTime);
        List<Orders> list =this.list(queryWrapper);
        return this.list();
    }


    @Override
    public Page<Orders> page(Integer pageNum,Integer pageSize,String id) {
        Page<Orders> page = new Page(pageNum,pageSize);
        LambdaQueryWrapper<Orders> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Orders::getDelFlag, ConstantsUtils.GL_NORMAL);
        if(StringUtils.isNotBlank(id)){
            lambdaQueryWrapper.like(Orders::getUserId,id);
        }
        lambdaQueryWrapper.orderByDesc(Orders::getCreateTime);
        page = this.page(page, lambdaQueryWrapper);
        return page;
    }

}