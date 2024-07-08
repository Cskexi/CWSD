package com.example.demo.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.cart.mapper.CartMapper;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.service.CartService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 购物车表 服务层实现类
    * </p>
*
* @author Csk
* @since 2024-07-03
*/
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper,Cart> implements CartService {

    @Override
    public Boolean addOrUpdate(Cart cart) {
        if(StringUtils.isBlank(cart.getId())){
            //add
            cart.setCreateTime(DateTool.getCurrTime());
            this.save(cart);
        }else{
            //update
            this.updateById(cart);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
        Cart cart = this.getById(id);
        cart.setDelFlag(ConstantsUtils.GL_DEL);
        this.updateById(cart);
        }

    }


    @Override
    public List<Cart> list() {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Cart::getDelFlag,ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(Cart::getCreateTime);
        List<Cart> list =this.list(queryWrapper);
        return this.list();
    }


    @Override
    public Page<Cart> page(Integer pageNum,Integer pageSize) {
        Page<Cart> page = new Page(pageNum,pageSize);
        LambdaQueryWrapper<Cart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Cart::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(Cart::getCreateTime);
        page = this.page(page, lambdaQueryWrapper);
        return page;
    }

}