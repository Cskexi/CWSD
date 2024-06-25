package com.example.demo.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.chapter4.entity.Dictype;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.order.mapper.OrderMapper;
import com.example.demo.order.entity.Order;
import com.example.demo.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 订单表 服务层实现类
    * </p>
*
* @author Csk
* @since 2023-12-21
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService {

    @Override
    public String addOrUpdate(Order order) {
        Random rand = new Random();
        if(StringUtils.isBlank(order.getId())){
            //add
            String str ="";
            for (int i = 0; i < 4; i++) {
                str = str + (char)(Math.random()*26+'a');
            }
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmm");
            String str2=sdf.format(new Date());
            order.setNo("no"+str2+(rand.nextInt(9000) + 1000)+str);
            order.setCreateTime(DateTool.getCurrTime());
            this.save(order);
        }else{
            //update
            this.updateById(order);
        }
        return order.getId();
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
            listIds.add(id);
        }
        this.removeByIds(listIds);
    }


    @Override
    public List<Order> list(String id )
    {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Order::getDelFlag, ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(Order::getCreateTime);
        List<Order> list =this.list(queryWrapper);
        return list;
    }


    @Override
    public List<Order> list2(String id)
    {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Order::getDelFlag, ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(Order::getCreateTime);
        List<Order> list =this.list(queryWrapper);
         return baseMapper.getList(list,id);
    }



    @Override
    public Page<Order> page(Integer pageNum,Integer pageSize) {
        Page<Order> page = new Page(pageNum,pageSize);

        return this.page(page);
    }

}