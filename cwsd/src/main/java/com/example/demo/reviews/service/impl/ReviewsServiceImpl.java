package com.example.demo.reviews.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.reviews.mapper.ReviewsMapper;
import com.example.demo.reviews.entity.Reviews;
import com.example.demo.reviews.service.ReviewsService;
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
* @since 2024-06-25
*/
@Service
public class ReviewsServiceImpl extends ServiceImpl<ReviewsMapper,Reviews> implements ReviewsService {

    @Override
    public Boolean addOrUpdate(Reviews reviews) {
        if(StringUtils.isBlank(reviews.getId())){
            //add
            reviews.setCreateTime(DateTool.getCurrTime());
            this.save(reviews);
        }else{
            //update
            this.updateById(reviews);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
        Reviews reviews = this.getById(id);
        reviews.setDelFlag(ConstantsUtils.GL_DEL);
        this.updateById(reviews);
        }

    }


    @Override
    public List<Reviews> list() {
        return this.list();
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