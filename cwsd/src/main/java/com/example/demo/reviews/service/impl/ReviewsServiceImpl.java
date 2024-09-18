package com.example.demo.reviews.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Store.entity.Store;
import com.example.demo.Store.service.StoreService;
import com.example.demo.chapter3.entity.User;
import com.example.demo.chapter3.service.UserService;
import com.example.demo.products.entity.Products;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.reviews.mapper.ReviewsMapper;
import com.example.demo.reviews.entity.Reviews;
import com.example.demo.reviews.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 评论表 服务层实现类
    * </p>
*
* @author Csk
* @since 2024-08-22
*/
@Service
public class ReviewsServiceImpl extends ServiceImpl<ReviewsMapper,Reviews> implements ReviewsService {

    @Autowired
    private UserService userService;

    @Override
    public Reviews addOrUpdate(Reviews reviews) {
        if(StringUtils.isBlank(reviews.getId())){
            //add
            reviews.setCreateTime(DateTool.getCurrTime());
            this.save(reviews);
        }else{
            //update
            this.updateById(reviews);
        }
        return reviews;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
        Reviews reviews = this.getById(id);
        reviews.setDelFlag(ConstantsUtils.GL_DEL);
        //this.updateById(reviews);
            this.removeById(reviews);
        }

    }


    @Override
    public List<Reviews> list(String productId) {
        QueryWrapper<Reviews> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(productId)) {
            queryWrapper.lambda().eq(Reviews::getProductId,productId);
        }
        queryWrapper.lambda().eq(Reviews::getDelFlag,ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(Reviews::getCreateTime);
        List<Reviews> list =this.list(queryWrapper);
        for(Reviews reviews : list){
            User user = userService.getById(reviews.getUserId());
            reviews.put("user",user);
        }
        return list;
    }


    @Override
    public Page<Reviews> page(Integer pageNum,Integer pageSize) {
        Page<Reviews> page = new Page(pageNum,pageSize);
        LambdaQueryWrapper<Reviews> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Reviews::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(Reviews::getCreateTime);
        page = this.page(page, lambdaQueryWrapper);
        return page;
    }

}