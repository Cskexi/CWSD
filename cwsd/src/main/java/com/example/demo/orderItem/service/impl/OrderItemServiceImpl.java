package com.example.demo.orderItem.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.orderItem.mapper.OrderItemMapper;
import com.example.demo.orderItem.entity.OrderItem;
import com.example.demo.orderItem.service.OrderItemService;
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
* @since 2023-12-21
*/
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper,OrderItem> implements OrderItemService {

    @Override
    public Boolean addOrUpdate(OrderItem orderItem) {
        if(StringUtils.isBlank(orderItem.getId())){
            //add
            orderItem.setCreateTime(DateTool.getCurrTime());
            this.save(orderItem);
        }else{
            //update
            this.updateById(orderItem);
        }
        return true;
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
    public List<OrderItem> list() {
        return this.list();
    }


    @Override
    public Page<OrderItem> page(Integer pageNum,Integer pageSize) {
        Page<OrderItem> page = new Page(pageNum,pageSize);

        return this.page(page);
    }

}