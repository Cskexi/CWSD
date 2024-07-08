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
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 子订单表 服务层实现类
    * </p>
*
* @author Csk
* @since 2024-07-03
*/
@Service
public class OrderItemsServiceImpl extends ServiceImpl<OrderItemsMapper,OrderItems> implements OrderItemsService {

    @Override
    public Boolean addOrUpdate(OrderItems orderItems) {
        if(StringUtils.isBlank(orderItems.getId())){
            //add
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