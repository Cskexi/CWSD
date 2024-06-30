package com.example.demo.categories.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.categories.mapper.CategoriesMapper;
import com.example.demo.categories.entity.Categories;
import com.example.demo.categories.service.CategoriesService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 字典表  服务层实现类
    * </p>
*
* @author Csk
* @since 2024-06-25
*/
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper,Categories> implements CategoriesService {

    @Override
    public Boolean addOrUpdate(Categories categories) {
        System.out.println(categories.getName());
        if(StringUtils.isBlank(categories.getId())){
            //add
            categories.setCreateTime(DateTool.getCurrTime());
            if (categories.getParentCategoryId() != null) {
                categories.setFirstCategoryId(findFatherById(categories.getParentCategoryId()).getId());
            }
            this.save(categories);
        }else{
            //update
            this.updateById(categories);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
        Categories categories = this.getById(id);
        categories.setDelFlag(ConstantsUtils.GL_DEL);
        this.updateById(categories);
        }

    }

    @Override
    public Categories findFatherById(String id) {
        Categories category = this.getById(id);

        if (category == null || category.getParentCategoryId() == null) {
            return category; // 如果不存在或者已经是顶级类别
        }
        // 递归查找顶级类别
        return findFatherById(category.getParentCategoryId());

    }



    @Override
    public List<Categories> list(String name) {
        QueryWrapper<Categories> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(name)){
            queryWrapper.lambda().like(Categories::getName,name);
        }
        queryWrapper.lambda().eq(Categories::getDelFlag,ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(Categories::getCreateTime);
        List<Categories> list =this.list(queryWrapper);
        return list;
    }


    @Override
    public Page<Categories> page(Integer pageNum,Integer pageSize) {
        Page<Categories> page = new Page(pageNum,pageSize);
        LambdaQueryWrapper<Categories> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Categories::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(Categories::getCreateTime);
        page = this.page(page, lambdaQueryWrapper);
        return page;
    }

}